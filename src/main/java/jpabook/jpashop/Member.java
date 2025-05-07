package jpabook.jpashop;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //난 mysql 연결해서 쓰기 때문에 이걸로 함
    //@GeneratedValue
    private Long id;

    private String username;
}
