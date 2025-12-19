package com.kh.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "options")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_id", nullable = false)
    private Decision decision;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String pros;

    @Column(columnDefinition = "TEXT")
    private String cons;

    @Column(columnDefinition = "TEXT")
    private String risks;

    public void changeDecision(Decision decision) {
        this.decision = decision;
    }
}