package com.ssafy.sunin.controller;

import com.ssafy.sunin.domain.MessageResponse;
import com.ssafy.sunin.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AlarmService alarmService;

//    @MessageMapping("/send/{userId}") //userId를 메세지를 받을 endpoint로 설정
//    @SendTo("/send/")
//    public void message(@DestinationVariable("userId") Long userId) {
//        System.out.println(userId);
//        simpMessagingTemplate.convertAndSend("/send/","알람성공");
//   }
   @MessageMapping("/send/{toUserId}/{fromUserId}") //userId를 메세지를 받을 endpoint로 설정
    @SendTo("/send/")
    public void message(@DestinationVariable("toUserId") Long toUserId,
                            @DestinationVariable("fromUserId") Long fromUserId) {
        System.out.println(toUserId+" "+fromUserId);
        List<String> messages = alarmService.getMessage(fromUserId,toUserId);
        simpMessagingTemplate.convertAndSend("/send/",messages);
   }
}