package com.ssafy.sunin.domain.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long incre;

    @Column(length =45)
    private String userId;

    @Column(length =45, unique = false)
    private String userEmail;

    @Column(length =1000, nullable = true)
    private String userPassword;

    @Column(length =45, nullable = true)
    private String userName;

    @Column(length =45, nullable = true)
    private String userNickname;

    @Column(length =45, nullable = true)
    private String userTel;

    @Column(length =45, nullable = true)
    private String userAddress;


    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role role; //권한


    private LocalDateTime createdDatetime;

    @PrePersist //디비에 insert 되기직전에 실행
    public void created_datetime() {
        this.created_datetime = LocalDateTime.now();
    }
}
