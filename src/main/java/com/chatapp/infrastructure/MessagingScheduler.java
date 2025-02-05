package com.chatapp.infrastructure;

import com.chatapp.domain.ChatMessage;
import com.chatapp.infrastructure.KafkaConstans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessagingScheduler {

    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // group id 중복 방지 -> 동일한 group id로 다수의 구독자가 있는 경우 구독자 중 한 명만 메시지를 수신하기 때문
    @KafkaListener(topics = KafkaConstans.TOPIC, groupId = "${kafka.group.id:${random.uuid}}")
    public void checkNotice(ChatMessage message){
        log.info("checkNotice call");
        try{
            messagingTemplate.setMessageConverter(new StringMessageConverter());
            messagingTemplate.convertAndSend("/subscribe/notice" + message.getRoom(), message.getUser() + "|" + message.getRoom() + ":" + message.getMessage() + " / " +message.getTime());
        }catch(Exception ex){
            log.error(ex.getMessage());
        }
    }

}
