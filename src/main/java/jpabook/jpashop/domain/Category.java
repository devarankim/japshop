package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name="category_items",//RDB 매핑테이블(CATEGORY_ITEM). 객체는 컬렉션끼리 다대다가 가능하지만 rdb는 안됨
            joinColumns = @JoinColumn(name="category_id"), //카테고리테이블과 조인하는 부분
            inverseJoinColumns = @JoinColumn(name="item_id") //item 테이블과 조인하는 부분
    )
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id") //자식->부모 관계이기 때문에 다(자식)대 일(부모)
    private Category parent;

    @OneToMany(mappedBy="parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 편의 메소드==//
    public void addChildCategory(Category child) {
        this.child.add(this);
        child.setParent(this); //자식을 넣으면 부모도 넣어줘야 한다!
    }
}
