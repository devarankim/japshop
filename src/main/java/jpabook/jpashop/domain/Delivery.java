package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy="delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) //ORDINAL: 숫자로 들어감(ex)1.2.3.4..) 나중에 상태값이 추가되면 꼬일 가능성 있어서 장애남, String:문자로 들어감.
    private DeliveryStatus status; //READY, COMP
}

