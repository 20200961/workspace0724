package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @Column(name = "user_id", nullable = false, length = 30)
    private String userId;

    @Column(name = "user_pwd", nullable = false, length = 100)
    private String userPwd;

    @Column(name = "user_name", nullable = false, length = 15)
    private String userName;

    @Column(name = "email", length = 254)
    private String email;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @CreationTimestamp
    @Column(name = "enroll_date", updatable = false)
    private LocalDateTime enrollDate;

    @UpdateTimestamp
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @Column(name = "status", nullable = false, length = 1)
    @ColumnDefault("'Y'")
    private String status;

    // 1:1 관계 - Profile
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    // 1:N 관계 - Board
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    // 1:N 관계 - Notice
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Notice> notices = new ArrayList<>();

    // 1:N 관계 - Reply
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

    public void updateMember(String userName, String email, String phone, String address) {
        if(userName != null) this.userName = userName;
        if(email != null) this.email = email;
        if(phone != null) this.phone = phone;
        if(address != null) this.address = address;
    }

    public void changePassword(String newPassword) {
        this.userPwd = newPassword;
    }

    public void withdraw() {
        this.status = "N";
    }
}