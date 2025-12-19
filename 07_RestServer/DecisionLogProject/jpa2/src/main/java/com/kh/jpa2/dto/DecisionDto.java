package com.kh.jpa2.dto;

import com.kh.jpa2.entity.Criteria;
import com.kh.jpa2.entity.Decision;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class DecisionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private Long memberId;
        private String title;
        private String type;
        private String situation;
        private String finalChoice;
        private List<OptionDto.Create> options;
        private CriteriaDto criteria;

        public Decision toEntity() {
            Criteria criteriaEntity = Criteria.builder()
                    .speed(criteria.getSpeed())
                    .cost(criteria.getCost())
                    .scalability(criteria.getScalability())
                    .teamCapability(criteria.getTeamCapability())
                    .build();

            return Decision.builder()
                    .title(title)
                    .type(type)
                    .situation(situation)
                    .finalChoice(finalChoice)
                    .decisionDate(LocalDateTime.now())
                    .criteria(criteriaEntity)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private Long memberId;
        private String title;
        private String type;
        private String situation;
        private String finalChoice;
        private LocalDateTime decisionDate;
        private LocalDateTime createdAt;
        private List<OptionDto.Response> options;
        private CriteriaDto criteria;
        private RetrospectiveDto.Response retrospective;

        public static Response of(Long id, Long memberId, String title, String type,
                                  String situation, String finalChoice, LocalDateTime decisionDate,
                                  LocalDateTime createdAt, List<OptionDto.Response> options,
                                  CriteriaDto criteria, RetrospectiveDto.Response retrospective) {
            return Response.builder()
                    .id(id)
                    .memberId(memberId)
                    .title(title)
                    .type(type)
                    .situation(situation)
                    .finalChoice(finalChoice)
                    .decisionDate(decisionDate)
                    .createdAt(createdAt)
                    .options(options)
                    .criteria(criteria)
                    .retrospective(retrospective)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ListResponse {
        private Long id;
        private Long memberId;
        private String title;
        private String type;
        private String finalChoice;
        private LocalDateTime decisionDate;

        public static ListResponse of(Long id, Long memberId, String title, String type,
                                      String finalChoice, LocalDateTime decisionDate) {
            return ListResponse.builder()
                    .id(id)
                    .memberId(memberId)
                    .title(title)
                    .type(type)
                    .finalChoice(finalChoice)
                    .decisionDate(decisionDate)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriteriaDto {
        private Integer speed;
        private Integer cost;
        private Integer scalability;
        private Integer teamCapability;
    }
}