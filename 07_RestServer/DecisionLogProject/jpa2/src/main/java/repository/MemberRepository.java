package com.kh.jpa2.repository;

import com.kh.jpa2.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    Long countByEmail(String email);
}