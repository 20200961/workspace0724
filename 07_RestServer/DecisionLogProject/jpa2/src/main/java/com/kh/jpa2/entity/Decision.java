package com.kh.jpa2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "decisions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Decision extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String situation;

    @Column(length = 100)
    private String finalChoice;

    @Column(nullable = false)
    private LocalDateTime decisionDate;

    @OneToMany(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Option> options = new ArrayList<>();

    @Embedded
    private Criteria criteria;

    @OneToOne(mappedBy = "decision", cascade = CascadeType.ALL, orphanRemoval = true)
    private Retrospective retrospective;

    public void changeMember(Member member) {
        this.member = member;
        if (!member.getDecisions().contains(this)) {
            member.getDecisions().add(this);
        }
    }

    public void addOption(Option option) {
        this.options.add(option);
        option.changeDecision(this);
    }

    public void setRetrospective(Retrospective retrospective) {
        this.retrospective = retrospective;
        if (retrospective != null) {
            retrospective.changeDecision(this);
        }
    }
}