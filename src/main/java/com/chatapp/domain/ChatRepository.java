package com.chatapp.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    ChatMessage save(ChatMessage message);

    List<ChatMessage> findByRoomOrderByTimeAsc(String room);

}
