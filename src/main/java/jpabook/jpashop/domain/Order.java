package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED) //생성자를 protected로 만들어서 다른 곳에서 객체 생성 못하도록!!
                                                  // 왜나면 객체 생성해서set 하고 이러면 유지보수도 어렵고 소스도 여기저기 분산되어 있어서 에러잡기도 힘ㄷ듬
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    //CascadeType.All로 하면 좋은 이유
    //1.원래 : orderItems가 jpa 3개 있다고 가정
    //// persist(orderItemA) persist(orderItemB) persist(orderItemc) persist(order)
    //2.All로 지정하면
    //// persist(order) 로 끝낼 수 있다. orderItems 컬렉션 A/B/C 3개를 다 persist해준다
    ///CascadeType.ALL 은 다른 곳에서 참조하지 않고 딱 오너가 명확한 로직일떄만 사용.


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간. java8부터는 해당 클래스 쓰면 hibernate가 알아서 자동 지원해줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //==연관관계 메소드==//
    //양방향 연관관계 세팅하려면 객체의 경우 값을 양쪽에 넣어줘야 한다.
    //연관관계 메소드는 핵심적으로 컨트롤하는 곳에 작성하는게 좋다.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성메소드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        Arrays.stream(orderItems).forEach(order::addOrderItem);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        return order;
    }

    //==비즈니스 로직 ==/
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }


    //==조회 로직 ==//

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
        return totalPrice;
    }
}

