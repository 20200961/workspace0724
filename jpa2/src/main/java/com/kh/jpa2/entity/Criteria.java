package com.kh.jpa2.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Criteria {
    private Integer speed;
    private Integer cost;
    private Integer scalability;
    private Integer teamCapability;
}