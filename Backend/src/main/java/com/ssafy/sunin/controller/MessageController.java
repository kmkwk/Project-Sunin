package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.user.User;
import com.ssafy.sunin.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AlarmService alarmService;

    // message
    // 요청 할때마다 여기에 보내고
    @MessageMapping("/send/{toUserId}/{messages}") //userId를 메세지를 받을 endpoint로 설정
    public void message(@DestinationVariable("toUserId") Long toUserId,
//                        @DestinationVariable("fromUserId") Long fromUserId
                        @DestinationVariable("messages") String messages) throws InterruptedException {
        Thread.sleep(1000);
//        User userId = alarmService.getUserId(toUserId);
//       List<String> messages = alarmService.getMessage(fromUserId,toUserId);
        simpMessagingTemplate.convertAndSend("/sub/"+toUserId, messages);

    }
}

// 1번을 구독해놓으면 서버에서