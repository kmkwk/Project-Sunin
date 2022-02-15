package com.ssafy.sunin.controller;

import com.ssafy.sunin.payload.response.alarm.AlarmResponse;
import com.ssafy.sunin.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final AlarmService alarmService;

    @MessageMapping("/send/{fromUserId}/{toUserId}/{messages}")
    public void message(@DestinationVariable("fromUserId") Long fromUserId,
                        @DestinationVariable("toUserId") Long toUserId,
                        @DestinationVariable("messages") String messages) throws InterruptedException {
        Thread.sleep(1000);
        if(!fromUserId.equals(toUserId)){
            alarmService.writeMessage(fromUserId,toUserId,messages);
            simpMessagingTemplate.convertAndSend("/sub/"+toUserId, messages);
        }
    }

    @GetMapping("/{toUserId}")
    public ResponseEntity<List<AlarmResponse>> getMessage(@PathVariable("toUserId") Long toUserId){
        log.info("getMessage");

        return ResponseEntity.ok(alarmService.getMessage(toUserId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") Long id){
        log.info("deleteMessage");
        alarmService.deleteMessage(id);
        return ResponseEntity.ok("success");
    }
}