package com.ssafy.sunin.controller;

import com.ssafy.sunin.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AlarmService alarmService;

    @MessageMapping("/send/{toUserId}/{messages}")
    public void message(@DestinationVariable("toUserId") Long toUserId,
                        @DestinationVariable("messages") String messages) throws InterruptedException {
        Thread.sleep(1000);
//        User userId = alarmService.getUserId(toUserId);
//       List<String> messages = alarmService.getMessage(fromUserId,toUserId);
        simpMessagingTemplate.convertAndSend("/sub/"+toUserId, messages);

    }
}