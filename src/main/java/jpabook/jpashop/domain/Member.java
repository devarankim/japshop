package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded //임베디드 내장 타입을 포함했다는 어노테이션
    private Address address;

    @OneToMany(mappedBy="member") //맵핑을 하는 컬렉션은 아니고 매핑된 거울이라는 뜻. 즉 읽기 전용. 이 값이 수정된다고 FK가 수정되진 않는다.
    private List<Order> orders = new ArrayList<>();

}
