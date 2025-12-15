package com.kh.jpa2.service;

import com.kh.jpa2.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final EntityManager em;

    @Transactional
    public Map<String, Object> register(Map<String, String> request) {
        String email = request.get("email");
        String name = request.get("name");

        // 이메일 중복 체크
        Long count = em.createQuery(
                        "SELECT COUNT(m) FROM Member m WHERE m.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();

        if (count > 0) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + email);
        }

        Member member = Member.builder()
                .email(email)
                .name(name)
                .build();

        em.persist(member);

        Map<String, Object> response = new HashMap<>();
        response.put("id", member.getId());
        response.put("email", member.getEmail());
        response.put("name", member.getName());
        response.put("createdAt", member.getCreatedAt());

        return response;
    }

    public Map<String, Object> login(Map<String, String> request) {
        String email = request.get("email");

        try {
            Member member = em.createQuery(
                            "SELECT m FROM Member m WHERE m.email = :email", Member.class)
                    .setParameter("email", email)
                    .getSingleResult();

            Map<String, Object> response = new HashMap<>();
            response.put("id", member.getId());
            response.put("email", member.getEmail());
            response.put("name", member.getName());

            return response;
        } catch (NoResultException e) {
            throw new IllegalArgumentException("해당 이메일의 회원이 존재하지 않습니다: " + email);
        }
    }

    public Map<String, Object> getMember(Long memberId) {
        Member member = em.find(Member.class, memberId);

        if (member == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("id", member.getId());
        response.put("email", member.getEmail());
        response.put("name", member.getName());
        response.put("createdAt", member.getCreatedAt());

        return response;
    }

    public Map<String, Long> getStats(Long memberId) {
        Member member = em.find(Member.class, memberId);

        if (member == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId);
        }

        Long total = em.createQuery(
                        "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId", Long.class)
                .setParameter("memberId", memberId)
                .getSingleResult();

        Long personal = em.createQuery(
                        "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId AND d.type = :type", Long.class)
                .setParameter("memberId", memberId)
                .setParameter("type", "개인")
                .getSingleResult();

        Long team = em.createQuery(
                        "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId AND d.type = :type", Long.class)
                .setParameter("memberId", memberId)
                .setParameter("type", "팀")
                .getSingleResult();

        Long withRetrospective = em.createQuery(
                        "SELECT COUNT(d) FROM Decision d WHERE d.member.id = :memberId AND d.retrospective IS NOT NULL", Long.class)
                .setParameter("memberId", memberId)
                .getSingleResult();

        Map<String, Long> stats = new HashMap<>();
        stats.put("total", total);
        stats.put("personal", personal);
        stats.put("team", team);
        stats.put("withRetrospective", withRetrospective);

        return stats;
    }
}