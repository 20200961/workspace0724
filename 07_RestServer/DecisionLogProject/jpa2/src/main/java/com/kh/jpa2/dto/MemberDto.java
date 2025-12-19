package com.kh.jpa2.dto;

import com.kh.jpa2.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String email;
        private String name;

        public Member toEntity() {
            return Member.builder()
                    .email(email)
                    .name(name)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Login {
        private String email;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String email;
        private String name;
        private LocalDateTime createdAt;

        public static Response of(Long id, String email, String name, LocalDateTime createdAt) {
            return Response.builder()
                    .id(id)
                    .email(email)
                    .name(name)
                    .createdAt(createdAt)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Stats {
        private Long total;
        private Long personal;
        private Long team;
        private Long withRetrospective;

        public static Stats of(Long total, Long personal, Long team, Long withRetrospective) {
            return Stats.builder()
                    .total(total)
                    .personal(personal)
                    .team(team)
                    .withRetrospective(withRetrospective)
                    .build();
        }
    }
}