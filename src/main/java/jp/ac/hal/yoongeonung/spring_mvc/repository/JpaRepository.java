package jp.ac.hal.yoongeonung.spring_mvc.repository;

import jp.ac.hal.yoongeonung.spring_mvc.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaRepository implements MemberRepository{

    private final EntityManager entityManager;

    @Autowired
    public JpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member as m where m.name = :name", Member.class).setParameter("name", name).getResultList().stream().findAny();
    }


    @Override
    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member as m", Member.class).getResultList();
    }
}
