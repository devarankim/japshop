package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(properties = "spring.jpa.hibernate.ddl-auto=create")
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional //엔티티 매니저를 통한 모든 데이터 변경은 항상 트랜잭션 안에서 이뤄져야야 한다. 테스트케이스에 있으면 롤백해줌
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //WHEN
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        //THEN
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);

     }

}