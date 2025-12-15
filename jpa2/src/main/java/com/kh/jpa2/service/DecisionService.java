package com.kh.jpa2.service;

import com.kh.jpa2.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DecisionService {

    private final EntityManager em;

    @Transactional
    public Map<String, Object> createDecision(Map<String, Object> request) {
        Long memberId = ((Number) request.get("memberId")).longValue();
        Member member = em.find(Member.class, memberId);

        if (member == null) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId);
        }

        // Criteria 생성
        @SuppressWarnings("unchecked")
        Map<String, Integer> criteriaMap = (Map<String, Integer>) request.get("criteria");
        Criteria criteria = Criteria.builder()
                .speed(criteriaMap.get("speed"))
                .cost(criteriaMap.get("cost"))
                .scalability(criteriaMap.get("scalability"))
                .teamCapability(criteriaMap.get("teamCapability"))
                .build();

        // Decision 생성
        Decision decision = Decision.builder()
                .member(member)
                .title((String) request.get("title"))
                .type((String) request.get("type"))
                .situation((String) request.get("situation"))
                .finalChoice((String) request.get("finalChoice"))
                .criteria(criteria)
                .build();

        em.persist(decision);

        // Options 생성
        @SuppressWarnings("unchecked")
        List<Map<String, String>> optionsList = (List<Map<String, String>>) request.get("options");

        if (optionsList != null) {
            for (Map<String, String> optionMap : optionsList) {
                Option option = Option.builder()
                        .name(optionMap.get("name"))
                        .pros(optionMap.get("pros"))
                        .cons(optionMap.get("cons"))
                        .risks(optionMap.get("risks"))
                        .build();

                decision.addOption(option);
                em.persist(option);
            }
        }

        return convertToResponse(decision);
    }

    public List<Map<String, Object>> getDecisions(Long memberId) {
        String jpql;

        if (memberId != null) {
            jpql = "SELECT d FROM Decision d WHERE d.member.id = :memberId ORDER BY d.decisionDate DESC";
            return em.createQuery(jpql, Decision.class)
                    .setParameter("memberId", memberId)
                    .getResultList()
                    .stream()
                    .map(this::convertToListResponse)
                    .collect(Collectors.toList());
        } else {
            jpql = "SELECT d FROM Decision d ORDER BY d.decisionDate DESC";
            return em.createQuery(jpql, Decision.class)
                    .getResultList()
                    .stream()
                    .map(this::convertToListResponse)
                    .collect(Collectors.toList());
        }
    }

    public Map<String, Object> getDecision(Long decisionId) {
        Decision decision = em.find(Decision.class, decisionId);

        if (decision == null) {
            throw new IllegalArgumentException("의사결정을 찾을 수 없습니다. ID: " + decisionId);
        }

        return convertToResponse(decision);
    }

    @Transactional
    public void deleteDecision(Long decisionId) {
        Decision decision = em.find(Decision.class, decisionId);

        if (decision == null) {
            throw new IllegalArgumentException("의사결정을 찾을 수 없습니다. ID: " + decisionId);
        }

        em.remove(decision);
    }

    @Transactional
    public Map<String, Object> addRetrospective(Long decisionId, Map<String, String> request) {
        Decision decision = em.find(Decision.class, decisionId);

        if (decision == null) {
            throw new IllegalArgumentException("의사결정을 찾을 수 없습니다. ID: " + decisionId);
        }

        Retrospective retrospective = Retrospective.builder()
                .actualResult(request.get("actualResult"))
                .wasCorrect(request.get("wasCorrect"))
                .improvements(request.get("improvements"))
                .build();

        decision.setRetrospective(retrospective);
        em.persist(retrospective);

        Map<String, Object> response = new HashMap<>();
        response.put("actualResult", retrospective.getActualResult());
        response.put("wasCorrect", retrospective.getWasCorrect());
        response.put("improvements", retrospective.getImprovements());
        response.put("updatedAt", retrospective.getUpdatedAt());

        return response;
    }

    // Helper methods
    private Map<String, Object> convertToResponse(Decision decision) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", decision.getId());
        response.put("memberId", decision.getMember().getId());
        response.put("title", decision.getTitle());
        response.put("type", decision.getType());
        response.put("situation", decision.getSituation());
        response.put("finalChoice", decision.getFinalChoice());
        response.put("decisionDate", decision.getDecisionDate());
        response.put("createdAt", decision.getCreatedAt());

        List<Map<String, Object>> options = decision.getOptions().stream()
                .map(this::convertOption)
                .collect(Collectors.toList());
        response.put("options", options);

        response.put("criteria", convertCriteria(decision.getCriteria()));

        if (decision.getRetrospective() != null) {
            response.put("retrospective", convertRetrospective(decision.getRetrospective()));
        } else {
            response.put("retrospective", null);
        }

        return response;
    }

    private Map<String, Object> convertToListResponse(Decision decision) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", decision.getId());
        response.put("memberId", decision.getMember().getId());
        response.put("title", decision.getTitle());
        response.put("type", decision.getType());
        response.put("finalChoice", decision.getFinalChoice());
        response.put("decisionDate", decision.getDecisionDate());

        return response;
    }

    private Map<String, Object> convertOption(Option option) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", option.getId());
        result.put("name", option.getName());
        result.put("pros", option.getPros());
        result.put("cons", option.getCons());
        result.put("risks", option.getRisks());
        return result;
    }

    private Map<String, Object> convertCriteria(Criteria criteria) {
        Map<String, Object> result = new HashMap<>();
        result.put("speed", criteria.getSpeed());
        result.put("cost", criteria.getCost());
        result.put("scalability", criteria.getScalability());
        result.put("teamCapability", criteria.getTeamCapability());
        return result;
    }

    private Map<String, Object> convertRetrospective(Retrospective retrospective) {
        Map<String, Object> result = new HashMap<>();
        result.put("actualResult", retrospective.getActualResult());
        result.put("wasCorrect", retrospective.getWasCorrect());
        result.put("improvements", retrospective.getImprovements());
        result.put("updatedAt", retrospective.getUpdatedAt());
        return result;
    }
}