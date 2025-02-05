package com.chatapp.presentation.api;

import com.chatapp.application.dto.ChatMessageResponseDto;
import com.chatapp.application.service.ChatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatApiController {

    @Autowired
    private ChatService chatService;

    // 참여한 채팅방에 이미 메시지가 존재할 경우 RDB에서 조회
    @GetMapping("/data")
    public ResponseEntity<List<ChatMessageResponseDto>> messageList(@RequestParam String room) {
        List<ChatMessageResponseDto> messages = chatService.getMessagesByRoomId(room);
        return ResponseEntity.ok(messages);
    }

}
