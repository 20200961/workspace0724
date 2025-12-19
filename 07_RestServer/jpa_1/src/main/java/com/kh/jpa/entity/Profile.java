package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "PROFILE")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "profile_image", length = 100)
    private String profileImage;

    @Column(name = "intro", length = 300)
    private String intro;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private Member member;

    public void updateProfile(String profileImage, String intro) {
        if(profileImage != null) this.profileImage = profileImage;
        if(intro != null) this.intro = intro;
    }
}