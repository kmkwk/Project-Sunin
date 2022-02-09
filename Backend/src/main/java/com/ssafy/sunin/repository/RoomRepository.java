package com.ssafy.sunin.repository;

import com.ssafy.sunin.domain.chat.Room;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoomRepository extends JpaRepository<Room, Long> {
}
