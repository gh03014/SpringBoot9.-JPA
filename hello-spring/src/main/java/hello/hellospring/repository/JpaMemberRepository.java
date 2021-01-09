package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //자동으로 insert문 구현
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 자동으로 select문 구현, id 컬럼값 조회
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result
                = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //모든 데이터를 List 객체에 담아서 반환
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
