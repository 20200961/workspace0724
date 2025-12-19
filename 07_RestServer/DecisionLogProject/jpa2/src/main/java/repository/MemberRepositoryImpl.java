package com.kh.jpa2.repository;

import com.kh.jpa2.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String jpql = "SELECT m FROM Member m WHERE m.email = :email";
        List<Member> members = em.createQuery(jpql, Member.class)
                .setParameter("email", email)
                .getResultList();

        return members.isEmpty() ? Optional.empty() : Optional.of(members.get(0));
    }

    @Override
    public Long countByEmail(String email) {
        String jpql = "SELECT COUNT(m) FROM Member m WHERE m.email = :email";
        return em.createQuery(jpql, Long.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}