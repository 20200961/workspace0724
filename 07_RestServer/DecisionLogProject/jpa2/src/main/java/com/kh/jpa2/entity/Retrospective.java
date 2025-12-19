package com.kh.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "retrospectives")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Retrospective extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_id", nullable = false, unique = true)
    private Decision decision;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String actualResult;

    @Column(nullable = false, length = 10)
    private String wasCorrect;

    @Column(columnDefinition = "TEXT")
    private String improvements;

    public void changeDecision(Decision decision) {
        this.decision = decision;
    }
}