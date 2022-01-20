package com.ssafy.sunin.domain.user;

import com.ssafy.sunin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@ToString
@Getter
@NoArgsConstructor
@Entity
public class SocialUser extends BaseTimeEntity {
    //사용자 정보를 담당할 도메인
    //findBy~를 쓰기위해서는 카멜 케이스가 인지하기 쉽다.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId; //id

    @Column(nullable = false, name="user_name")
    private String userName; //이름

    @Column(name="user_email")
    private String userEmail; //이메일

    @Column(name="user_picture")
    private String userPicture; //사진

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role role; //권한

    @Enumerated(EnumType.STRING)
    @Column(name="user_social")
    private Social social; //플랫폼

    @Builder
    public SocialUser(String userName, String userEmail, String userPicture, Role role, Social social){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPicture = userPicture;
        this.role = role;
        this.social = social;
    }

    public SocialUser update(String userName, String userPicture){
        this.userName = userName;
        this.userPicture = userPicture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}