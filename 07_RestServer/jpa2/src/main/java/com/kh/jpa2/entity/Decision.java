package com.kh.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "decisions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String situation;

    @Column
    private String finalChoice;

    @CreationTimestamp
    private LocalDateTime decisionDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Option> options = new ArrayList<>();

    @Embedded
    private Criteria criteria;

    @OneToOne(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    private Retrospective retrospective;

    public void addOption(Option option) {
        options.add(option);
        option.setDecision(this);
    }

    public void setRetrospective(Retrospective retrospective) {
        this.retrospective = retrospective;
        if (retrospective != null) {
            retrospective.setDecision(this);
        }
    }
}