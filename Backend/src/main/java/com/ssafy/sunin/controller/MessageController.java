package com.ssafy.sunin.controller;

import com.ssafy.sunin.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AlarmService alarmService;

    // message
   @MessageMapping("/send/{toUserId}/{fromUserId}") //userId를 메세지를 받을 endpoint로 설정
    public void message(@DestinationVariable("toUserId") Long toUserId,
                        @DestinationVariable("fromUserId") Long fromUserId) {

       List<String> messages = alarmService.getMessage(fromUserId,toUserId);
        simpMessagingTemplate.convertAndSend("/sub/" + toUserId, messages);

   }
}