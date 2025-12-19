package com.kh.jpa2.dto;

import com.kh.jpa2.entity.Option;
import lombok.*;

public class OptionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private String name;
        private String pros;
        private String cons;
        private String risks;

        public Option toEntity() {
            return Option.builder()
                    .name(name)
                    .pros(pros)
                    .cons(cons)
                    .risks(risks)
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
        private String name;
        private String pros;
        private String cons;
        private String risks;

        public static Response of(Long id, String name, String pros, String cons, String risks) {
            return Response.builder()
                    .id(id)
                    .name(name)
                    .pros(pros)
                    .cons(cons)
                    .risks(risks)
                    .build();
        }
    }
}