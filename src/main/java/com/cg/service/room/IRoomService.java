package com.cg.service.room;

import com.cg.model.Room;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IRoomService extends IGeneralService<Room, Long> {
    List<Room> getAllBySpecialityId(Long specialityId);

}
