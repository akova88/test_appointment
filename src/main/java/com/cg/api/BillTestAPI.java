package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dtos.billTest.BillTestReqDTO;
import com.cg.model.dtos.billTest.BillTestResDTO;
import com.cg.model.dtos.locationRegion.LocationRegionCreReqDTO;
import com.cg.model.enums.EBookTime;
import com.cg.model.enums.EGender;
import com.cg.service.billTest.IBillTestService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.dm_goi.IDMGoiService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/bill_tests")
public class BillTestAPI {
    @Autowired
    private IBillTestService billTestService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IDMGoiService dmGoiService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BillTestResDTO> billTestResDTOS = billTestService.findAllBillTestResDTO();

        return new ResponseEntity<>(billTestResDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid BillTestReqDTO billTestReqDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String maGoi = billTestReqDTO.getMaGoi();
        DM_Goi dmGoi = dmGoiService.findDM_GoiByMaGoi(maGoi);
        if (dmGoi == null) {
            throw new DataInputException("Mã nhóm dịch vụ không tồn tại");
        }
        String bookTimeName = billTestReqDTO.getTimeBookName();
        String takeDateStr = billTestReqDTO.getTakeDate();
        EBookTime eBookTime = EBookTime.valueOf(bookTimeName);
        String bookTimeValue = eBookTime.getValue();
        Date takeDate = DateFormat.parse(takeDateStr);

        if (DateFormat.isAfterBookingDate(takeDateStr)) {
            throw new DataInputException("Vui lòng đặt lịch hẹn sau ngày hôm nay");
        }

        if (DateFormat.isEqualBookingDate(takeDateStr) && DateFormat.isAfterBookingTime(bookTimeValue)) {
            throw new DataInputException("Giờ lâý mẫu không hợp lệ");
        }

        String eGenderName = billTestReqDTO.getGenderName();
        EGender eGender;
        try {
            eGender = EGender.valueOf(eGenderName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Giới tính không tồn tại");
        }

        LocationRegionCreReqDTO locationRegionCreReqDTO = billTestReqDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionCreReqDTO.toLocationRegion();

        Long userId = Long.parseLong(billTestReqDTO.getUserId());
        User user = userService.findById(userId).orElseThrow(()-> new DataInputException("Người dùng không tồn tại"));

        BillTest billTest = billTestReqDTO.toBillTest(dmGoi, eBookTime, eGender, user);


        BillTest billTestNew = billTestService.create(billTest, locationRegion);

        return new ResponseEntity<>(billTestNew.toBillTestResDTO(),HttpStatus.CREATED);
    }
}
