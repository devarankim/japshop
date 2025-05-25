package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED) //protected OrderItem() {}와 같은 의미!!!!
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count; // 주문 수량

   // protected OrderItem() {}  //객체 생성해서 set할 수 없도록 막음. protected로 다른 개발자들에게 알려주는거임. 객체 생성하지 말라!

    //==생성메소드==//
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem(); //추후 할인같은 이슈도 있기 때문
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스로직==//
    public void cancel() {
        getItem().addStock(count);
    }

    //==조회로직==//

    /**
     * 주문상품 전체 조회
     */
    public int getTotalPrice() {
        return getOrderPrice()*getCount();
    }

}
