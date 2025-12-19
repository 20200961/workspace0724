package com.kh.jpa2.service;

import com.kh.jpa2.dto.DecisionDto;
import com.kh.jpa2.dto.OptionDto;
import com.kh.jpa2.dto.RetrospectiveDto;
import com.kh.jpa2.entity.Decision;
import com.kh.jpa2.entity.Member;
import com.kh.jpa2.entity.Option;
import com.kh.jpa2.entity.Retrospective;
import com.kh.jpa2.repository.DecisionRepository;
import com.kh.jpa2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DecisionService {

    private final DecisionRepository decisionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public DecisionDto.Response createDecision(DecisionDto.Create createDto) {
        // 회원 조회
        Member member = memberRepository.findById(createDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + createDto.getMemberId()));

        // Decision 생성
        Decision decision = createDto.toEntity();
        decision.changeMember(member);

        // Options 추가
        if (createDto.getOptions() != null) {
            for (OptionDto.Create optionDto : createDto.getOptions()) {
                Option option = optionDto.toEntity();
                decision.addOption(option);
            }
        }

        decisionRepository.save(decision);

        return convertToResponse(decision);
    }

    public List<DecisionDto.ListResponse> getDecisions(Long memberId) {
        List<Decision> decisions;

        if (memberId != null) {
            decisions = decisionRepository.findByMemberId(memberId);
        } else {
            decisions = decisionRepository.findAll();
        }

        return decisions.stream()
                .map(this::convertToListResponse)
                .collect(Collectors.toList());
    }

    public DecisionDto.Response getDecision(Long decisionId) {
        Decision decision = decisionRepository.findById(decisionId)
                .orElseThrow(() -> new IllegalArgumentException("의사결정을 찾을 수 없습니다. ID: " + decisionId));

        return convertToResponse(decision);
    }

    @Transactional
    public void deleteDecision(Long decisionId) {
        Decision decision = decisionRepository.findById(decisionId)
                .orElseThrow(() -> new IllegalArgumentException("의사결정을 찾을 수 없습니다. ID: " + decisionId));

        decisionRepository.delete(decision);
    }

    @Transactional
    public RetrospectiveDto.Response addRetrospective(Long decisionId, RetrospectiveDto.Create createDto) {
        Decision decision = decisionRepository.findById(decisionId)
                .orElseThrow(() -> new IllegalArgumentException("의사결정을 찾을 수 없습니다. ID: " + decisionId));

        Retrospective retrospective = createDto.toEntity();
        decision.setRetrospective(retrospective);

        return RetrospectiveDto.Response.of(
                retrospective.getActualResult(),
                retrospective.getWasCorrect(),
                retrospective.getImprovements(),
                retrospective.getUpdatedAt()
        );
    }

    // Helper methods
    private DecisionDto.Response convertToResponse(Decision decision) {
        List<OptionDto.Response> options = decision.getOptions().stream()
                .map(option -> OptionDto.Response.of(
                        option.getId(),
                        option.getName(),
                        option.getPros(),
                        option.getCons(),
                        option.getRisks()
                ))
                .collect(Collectors.toList());

        DecisionDto.CriteriaDto criteriaDto = new DecisionDto.CriteriaDto(
                decision.getCriteria().getSpeed(),
                decision.getCriteria().getCost(),
                decision.getCriteria().getScalability(),
                decision.getCriteria().getTeamCapability()
        );

        RetrospectiveDto.Response retrospectiveDto = null;
        if (decision.getRetrospective() != null) {
            Retrospective retro = decision.getRetrospective();
            retrospectiveDto = RetrospectiveDto.Response.of(
                    retro.getActualResult(),
                    retro.getWasCorrect(),
                    retro.getImprovements(),
                    retro.getUpdatedAt()
            );
        }

        return DecisionDto.Response.of(
                decision.getId(),
                decision.getMember().getId(),
                decision.getTitle(),
                decision.getType(),
                decision.getSituation(),
                decision.getFinalChoice(),
                decision.getDecisionDate(),
                decision.getCreatedAt(),
                options,
                criteriaDto,
                retrospectiveDto
        );
    }

    private DecisionDto.ListResponse convertToListResponse(Decision decision) {
        return DecisionDto.ListResponse.of(
                decision.getId(),
                decision.getMember().getId(),
                decision.getTitle(),
                decision.getType(),
                decision.getFinalChoice(),
                decision.getDecisionDate()
        );
    }
}