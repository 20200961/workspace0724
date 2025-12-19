package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "NOTICE")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Long noticeNo;

    @Column(name = "notice_title", nullable = false, length = 30)
    private String noticeTitle;

    @Column(name = "notice_content", nullable = false, length = 200)
    private String noticeContent;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_writer", nullable = false)
    private Member member;

    public void updateNotice(String noticeTitle, String noticeContent) {
        if(noticeTitle != null) this.noticeTitle = noticeTitle;
        if(noticeContent != null) this.noticeContent = noticeContent;
    }
}