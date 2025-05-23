package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //롤백 가능. 테스트코드에서 트랜잭션은 인서트쿼리 조차 나가지 않는다.
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //@Autowired EntityManager em; //굳이 인서트쿼리 보고싶으면 첫번째순서

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kimaran");

        //WHEN
        Long savedId = memberService.join(member);

        //THEN
        //em.flush(); //굳이 인서트커리 보고싶으면 두번째순서. DB의 쿼리가 날라가는 것
        assertEquals(member, memberRepository.findOne(savedId));

     }

     @Test()
     public void 중복_회원_예외() throws Exception {
         //given
         Member member1 = new Member();
         member1.setName("kimaran");

         Member member2 = new Member();
         member2.setName("kimaran");

         //WHEN
         memberService.join(member1);

         //THEN
         assertThrows(IllegalStateException.class, () -> {
             memberService.join(member2);
         }); //예외가 발생해야 한다

      }

}