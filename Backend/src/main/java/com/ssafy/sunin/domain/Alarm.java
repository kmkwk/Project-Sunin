package com.ssafy.sunin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "alarm")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long fromUserId;

    private String message;

    private Long toUserId;

    @Builder
    public Alarm(Long fromUserId, String message, Long toUserId) {
        this.fromUserId = fromUserId;
        this.message = message;
        this.toUserId = toUserId;
    }
}
