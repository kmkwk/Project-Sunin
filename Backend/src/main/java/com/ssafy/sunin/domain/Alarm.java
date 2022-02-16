package com.ssafy.sunin.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "alarm")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fromUserId;
    private String message;
    private Long toUserId;

    public static Alarm alarm(Long fromUserId, Long toUserId, String message){
        return Alarm.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .message(message)
                .build();
    }

    @Builder
    public Alarm(Long id, Long fromUserId, String message, Long toUserId) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.message = message;
        this.toUserId = toUserId;
    }
}
