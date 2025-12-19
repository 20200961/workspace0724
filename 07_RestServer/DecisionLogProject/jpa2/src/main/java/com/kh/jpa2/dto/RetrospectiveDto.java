package com.kh.jpa2.dto;

import com.kh.jpa2.entity.Retrospective;
import lombok.*;

import java.time.LocalDateTime;

public class RetrospectiveDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String actualResult;
        private String wasCorrect;
        private String improvements;

        public Retrospective toEntity() {
            return Retrospective.builder()
                    .actualResult(actualResult)
                    .wasCorrect(wasCorrect)
                    .improvements(improvements)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String actualResult;
        private String wasCorrect;
        private String improvements;
        private LocalDateTime updatedAt;

        public static Response of(String actualResult, String wasCorrect,
                                  String improvements, LocalDateTime updatedAt) {
            return Response.builder()
                    .actualResult(actualResult)
                    .wasCorrect(wasCorrect)
                    .improvements(improvements)
                    .updatedAt(updatedAt)
                    .build();
        }
    }
}