package com.example.demo.services;

import com.example.demo.repository.RoomBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomBookingService {

    private final RoomBookingRepository roomBookingRepository;

}
