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
    private String user_password;

    @Column(length =45, nullable = false)
    private String user_name;

    @Column(length =45, nullable = false)
    private String user_nickname;

    @Column(length =45, nullable = false)
    private String user_tel;

    @Column(length =45, nullable = false)
    private String user_address;

    private LocalDateTime created_datetime;

    @OneToMany(mappedBy = "user")
    private List<Follower> follower = new ArrayList<>();

    @PrePersist //디비에 insert 되기직전에 실행
    public void created_datetime() {
        this.created_datetime = LocalDateTime.now();
    }
}
