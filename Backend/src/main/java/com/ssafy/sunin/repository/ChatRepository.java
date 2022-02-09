package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.chat.Chat;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface ChatRepository extends CrudRepository<Chat, Long> {

    List<Chat> findAllByRoomId(Long roomId);
}
