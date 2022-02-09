package com.ssafy.sunin.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/{roomId}")
    @SendTo("/room/{roomId}")
    public ResponseEntity<ChatMessage> ChatMessage(@DestinationVariable Long roomId, ChatMessage message){
        Chat chat = chatService.createChat(roomId, message.getSender(), message.getMessage());
        ChatMessage chatMessage = ChatMessage.builder().roomId(roomId).sender(chat.getSender()).message(chat.getMessage()).build();
        return ResponseEntity.ok(chatMessage);
    }
}
