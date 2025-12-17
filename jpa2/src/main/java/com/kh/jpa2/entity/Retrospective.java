package com.kh.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "retrospectives")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Retrospective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "decision_id", nullable = false)
    private Decision decision;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String actualResult;

    @Column(nullable = false)
    private String wasCorrect;

    @Column(columnDefinition = "TEXT")
    private String improvements;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}