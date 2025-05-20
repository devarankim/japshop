package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext //JPA의 엔티티 매니저를 SPRING이 생성한 엔티티 매니저에 주입을 해준다.
    private EntityManager em;

    public void save(Member member) {
        em.persist(member); //저장
    }

    public Member findOne(Long id){
        return em.find(Member.class, id); //id값을 가지고 Member을 찾아서 반환해주는 것, Member member = em.find(Member.class, id)와 동일
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) //JQPL은 엔티티 객체를 대상으로 쿼리를 짬. Member 엔티티 객체를 m으로 alias 주고 조회
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
