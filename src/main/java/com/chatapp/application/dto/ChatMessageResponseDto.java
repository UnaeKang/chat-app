package com.chatapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChatMessageResponseDto {
    private Long id;
    private String user;
    private String message;
    private String time;
}
