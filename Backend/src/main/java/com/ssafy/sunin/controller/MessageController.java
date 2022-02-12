package com.ssafy.sunin.controller;

import com.ssafy.sunin.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AlarmService alarmService;

    // message
    // 요청 할때마다 여기에 보내고
   @MessageMapping("/send/{toUserId}") //userId를 메세지를 받을 endpoint로 설정
    public void message(@DestinationVariable("toUserId") Long toUserId,
//                        @DestinationVariable("fromUserId") Long fromUserId
                        @DestinationVariable("messages") String messages) {

//       List<String> messages = alarmService.getMessage(fromUserId,toUserId);
        simpMessagingTemplate.convertAndSend("/sub/" + toUserId, messages);

   }
}