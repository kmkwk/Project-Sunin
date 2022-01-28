package com.ssafy.sunin.domain.user;

import com.ssafy.sunin.domain.Follower;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    @Column(length =45, unique = true)
    private String userId;

    @Column(length =45, nullable = false)
    private String userName;

    @Column(length =45, nullable = false)
    private String userPassword;

    @Column(length =45, nullable = false)
    private String userNickname;

    @Column(length =45, nullable = false)
    private String userTel;

    @Column(length =45, nullable = false)
    private String userAddress;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role role; //권한

    private LocalDateTime createdDatetime;

    @OneToMany(mappedBy = "user")
    private List<Follower> follower = new ArrayList<>();

    private Integer suninDays;

    @PrePersist //디비에 insert 되기직전에 실행
    public void created_datetime() {
        this.createdDatetime = LocalDateTime.now();
    }
}
