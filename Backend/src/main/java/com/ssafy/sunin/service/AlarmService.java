package com.ssafy.sunin.service;

import com.ssafy.sunin.domain.Alarm;
import com.ssafy.sunin.domain.Message;
import com.ssafy.sunin.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public List<String> getMessage(Long fromUserId, Long toUserId){
        String message = fromUserId+"가 팔로워를 했습니다";
        Alarm alarm = Alarm.builder()
            .fromUserId(fromUserId)
            .message(message)
            .toUserId(toUserId)
            .build();

        alarmRepository.save(alarm);

        // toUserId의 모든 메시지
        List<Alarm> alarms= alarmRepository.findAllByToUserId(toUserId);
        List<String> messages = new ArrayList<>();

        for (int i = 0; i < alarms.size(); i++) {
            messages.add(alarms.get(i).getMessage());
        }

        return messages;
    }


}
