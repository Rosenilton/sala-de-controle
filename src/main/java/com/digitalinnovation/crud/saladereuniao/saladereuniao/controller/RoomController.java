package com.digitalinnovation.crud.saladereuniao.saladereuniao.controller;

import com.digitalinnovation.crud.saladereuniao.saladereuniao.exception.ResourceNotFoundException;
import com.digitalinnovation.crud.saladereuniao.saladereuniao.model.Room;
import com.digitalinnovation.crud.saladereuniao.saladereuniao.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@AllArgsConstructor
public class RoomController {

    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {
        Room room = roomService.getRoomById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found :: " + roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity <Room> updateRoom(@PathVariable(value = "id") Long roomId,
                                            @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room =  roomService.updateRoom(roomId, roomDetails);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
            throws ResourceNotFoundException {
        return roomService.deleteRoom(roomId);
    }

}