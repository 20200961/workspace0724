package com.kh.jpa2.repository;

import com.kh.jpa2.entity.Decision;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DecisionRepositoryImpl implements DecisionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Decision save(Decision decision) {
        em.persist(decision);
        return decision;
    }

    @Override
    public Optional<Decision> findById(Long id) {
        return Optional.ofNullable(em.find(Decision.class, id));
    }

    @Override
    public List<Decision> findAll() {
        String jpql = "SELECT d FROM Decision d ORDER BY d.decisionDate DESC";
        return em.createQuery(jpql, Decision.class).getResultList();
    }

    @Override
    public List<Decision> findByMemberId(Long memberId) {
        String jpql = "SELECT d FROM Decision d WHERE d.member.id = :memberId ORDER BY d.decisionDate DESC";
        return em.createQuery(jpql, Decision.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    @Override
    public void delete(Decision decision) {
        em.remove(decision);
    }

    @Override
    public Long countByMemberId(Long memberId) {
        String jpql = "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId";
        return em.createQuery(jpql, Long.class)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }

    @Override
    public Long countByMemberIdAndType(Long memberId, String type) {
        String jpql = "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId AND d.type = :type";
        return em.createQuery(jpql, Long.class)
                .setParameter("memberId", memberId)
                .setParameter("type", type)
                .getSingleResult();
    }

    @Override
    public Long countByMemberIdWithRetrospective(Long memberId) {
        String jpql = "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId AND d.retrospective IS NOT NULL";
        return em.createQuery(jpql, Long.class)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }
}