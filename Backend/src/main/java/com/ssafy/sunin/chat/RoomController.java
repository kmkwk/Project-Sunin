package com.ssafy.sunin.chat;


import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final ChatService chatService;

    @GetMapping("/{roomId}")
    public ResponseEntity joinRoom(@PathVariable Long roomId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/room")
    public ResponseEntity createRoom(RoomForm form){
        chatService.createRoom(form.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/roomForm")
    public ResponseEntity roomForm(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
