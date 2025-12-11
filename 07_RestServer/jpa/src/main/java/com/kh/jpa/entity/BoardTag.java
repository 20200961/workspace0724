package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "BOARD_TAG")
public class BoardTag {

    @EmbeddedId
    private BoardTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardNo")
    @JoinColumn(name = "board_no")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Embeddable
    @Getter
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class BoardTagId implements Serializable {
        @Column(name = "board_no")
        private Long boardNo;

        @Column(name = "tag_id")
        private Long tagId;
    }
}