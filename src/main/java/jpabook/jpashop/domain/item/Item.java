package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //joined:정교화된 스타일, SINGLE_TABLE:한 테이블에 다 넣기, tableperclass: 클래스별로 테이블 생성
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //도메인 주도 설계할 때 엔티티 자체 안에서 해결할 수 있는 로직들은 엔티티 안에 비스니스 로직 넣는게 좋다.
    //객체지향적으로 좋다. 응집도가 높아짐
    //setter는 뺴고 아래와 같은 비즈니스 로직으로 데이터를 다뤄야 한다.

    //==비즈니스 로직 ==//
    /**
     * stock증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            System.out.println("restStock = " + restStock);
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
