package com.ssafy.sunin.domain;

import com.ssafy.sunin.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "follower")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follower{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follower_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_member",referencedColumnName = "no")
    private User followerMember;

    @CreatedDate //entity가 생성되어 저장될 때 시간이 자동 저장됨
    @Column(name = "created_datetime")
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder
    public Follower(Long id, User user, User followerMember, LocalDateTime createDate) {
        this.id = id;
        this.user = user;
        this.followerMember = followerMember;
        this.createDate = createDate;
    }
}