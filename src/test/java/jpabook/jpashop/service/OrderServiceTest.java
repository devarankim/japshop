package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    EntityManager em;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        Book book = new Book();
        book.setName("아란짱");
        book.setPrice(10000);
        book.setStockQuantity(10);


        //WHEN

        //THEN

     }

     @Test
     public void 주문취소() throws Exception {
         //given

         //WHEN

         //THEN

      }


      @Test
      public void 상품주문_재교수량초과() throws Exception {
          //given

          //WHEN

          //THEN

       }

}