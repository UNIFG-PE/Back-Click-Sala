package com.example.demo.services;

import com.example.demo.entities.Room;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room createRoom (Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom (Long id, Room room) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        existingRoom.setIdentifier(room.getIdentifier());
        existingRoom.setFloor(room.getFloor());
        existingRoom.setCapacity(room.getCapacity());
        existingRoom.setDescription(room.getDescription());
        existingRoom.setStatus(room.getStatus());
        existingRoom.setCampus(room.getCampus());
        existingRoom.setCategory(room.getCategory());

        return roomRepository.save(existingRoom);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
