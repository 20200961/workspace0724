package com.kh.jpa2.repository;

import com.kh.jpa2.entity.Decision;

import java.util.List;
import java.util.Optional;

public interface DecisionRepository {
    Decision save(Decision decision);
    Optional<Decision> findById(Long id);
    List<Decision> findAll();
    List<Decision> findByMemberId(Long memberId);
    void delete(Decision decision);
    Long countByMemberId(Long memberId);
    Long countByMemberIdAndType(Long memberId, String type);
    Long countByMemberIdWithRetrospective(Long memberId);
}
