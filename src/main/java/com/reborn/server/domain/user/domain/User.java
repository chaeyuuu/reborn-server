package com.reborn.server.domain.user.domain;

import com.reborn.server.domain.auth.domain.oauth.OauthProvider;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "nickname")
    private String nickName;

    private String name;

    private String email;

    @Column(name = "o_auth_provider")
    private OauthProvider oauthProvider;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "profile_image")
    private String profileImg;

    @Column(name = "reborn_temperature")
    private String rebornTemperature; // 리본 온도

    @Column(name = "employment_status")
    private String employmentStatus; // "재직" or "퇴직"

    @Column(name = "region")
    private String region; // 동네

    @ElementCollection
    @Column(name="interested_field")
    private List<String> interestedField; // 관심 분야

    @Builder
    public User(String name, String email, OauthProvider oauthProvider, String introduce, String profileImg, String rebornTemperature, String employmentStatus, String region, List<String> interestedField) {
        this.name = name;
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.introduce = introduce;
        this.profileImg = profileImg;
        this.rebornTemperature = rebornTemperature;
        this.employmentStatus = employmentStatus;
        this.region = region;
        this.interestedField = interestedField;
    }

    public static User of(String name, String email, OauthProvider oauthProvider, String profileImg) {
        return User.builder()
                .name(name)
                .email(email)
                .oauthProvider(oauthProvider)
                .profileImg(profileImg)
                .build();
    }

    public void updateUserProfile(String nickName, String profileImg, String employmentStatus) {
        this.nickName = nickName;
        this.profileImg = profileImg;
        this.employmentStatus = employmentStatus;
    }

    public void updateUserInterests(List<String> interestedField) {
        this.interestedField = interestedField;
    }

    public void updateUserRegion(String region) {
        this.nickName = region;
    }


    public void updateOnboardingInfo(String employmentStatus, String region, String interestedField) {
        this.employmentStatus = employmentStatus;
        this.region = region;
        this.interestedField = interestedField;
    }
}
