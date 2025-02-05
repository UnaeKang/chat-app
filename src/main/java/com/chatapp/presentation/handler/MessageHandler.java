package com.chatapp.presentation.handler;

import com.chatapp.domain.ChatMessage;
import com.chatapp.domain.ChatRepository;
import com.chatapp.infrastructure.KafkaConstans;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageHandler {

    @Autowired
    private KafkaTemplate<String, ChatMessage> kafkaTemplate;
    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    @MessageMapping("/message")
    @SendTo("/topic/greetings")
    public void greeting(ChatMessage message) throws Exception {
        log.info("message received, message:{}", message.toString());

        message.setTime(LocalDateTime.now().toString());

        // DB 저장
        chatRepository.save(message);

        // Kafka push
        kafkaTemplate.send(KafkaConstans.TOPIC, message).get();
    }

}
