package com.chatapp.application.service;

import com.chatapp.application.dto.ChatMessageResponseDto;
import com.chatapp.domain.ChatRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<ChatMessageResponseDto> getMessagesByRoomId(String room) {
        return chatRepository.findByRoomOrderByTimeAsc(room).stream()
                .map(chat -> new ChatMessageResponseDto(
                        chat.getId()
                        , chat.getUser()
                        , chat.getMessage()
                        , chat.getTime()
                ))
                .collect(Collectors.toList());
    }

}
