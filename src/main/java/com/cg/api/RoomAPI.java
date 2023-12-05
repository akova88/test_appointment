package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Room;
import com.cg.model.Speciality;
import com.cg.model.dtos.room.RoomCreReqDTO;
import com.cg.model.dtos.room.RoomResDTO;
import com.cg.service.room.IRoomService;
import com.cg.service.speciality.ISpecialityService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rooms")
public class RoomAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ISpecialityService specialityService;

    @Autowired
    private IRoomService roomService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<RoomResDTO> roomResDTOList = new ArrayList<>();
        List<Room> rooms = roomService.findAll();

        for (Room room: rooms){
            RoomResDTO roomResDTO = room.toRoomResDTO();
            roomResDTOList.add(roomResDTO);
        }

        return new ResponseEntity<>(roomResDTOList,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoomCreReqDTO roomCreReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Long specialityId = Long.valueOf(roomCreReqDTO.getSpecialityId());
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()->{
            throw new DataInputException("Mã khoa không tồn tại");
        });

        Room room = roomCreReqDTO.toRoom(speciality);
        Room newRoom = roomService.save(room);
        RoomResDTO roomResDTO = newRoom.toRoomResDTO();

        return new ResponseEntity<>(roomResDTO, HttpStatus.CREATED);
    }

    @GetMapping("/speciality/{specialityId}")
    public ResponseEntity<?> getRoomsBySpec(@PathVariable("specialityId") String specialityIdStr){
        if (!ValidateUtil.isNumberValid(specialityIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã chuyên khoa không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long specialityId = Long.parseLong(specialityIdStr);
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()->{
            throw new DataInputException("Chuyên khoa không tồn tại");
        });

        List<Room> rooms = roomService.getAllBySpecialityId(specialityId);
        List<RoomResDTO> roomResDTOList = new ArrayList<>();

        for (Room room: rooms){
            RoomResDTO roomResDTO = room.toRoomResDTO();
            roomResDTOList.add(roomResDTO);
        }

        return new ResponseEntity<>(roomResDTOList,HttpStatus.OK);
    }
}
