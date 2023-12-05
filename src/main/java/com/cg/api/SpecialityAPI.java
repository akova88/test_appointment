package com.cg.api;

import com.cg.model.Speciality;
import com.cg.model.dtos.speciality.SpecialityCreReqDTO;
import com.cg.model.dtos.speciality.SpecialityResDTO;
import com.cg.service.speciality.ISpecialityService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/specialities")
public class SpecialityAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ISpecialityService specialityService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<SpecialityResDTO> specialityResDTOS = new ArrayList<>();
        List<Speciality> specialities = specialityService.findAll();

        for (Speciality speciality: specialities){
            SpecialityResDTO specialityResDTO = speciality.toSpecialityResDTO();
            specialityResDTOS.add(specialityResDTO);
        }

        return new ResponseEntity<>(specialityResDTOS,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody SpecialityCreReqDTO specialityCreReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Speciality speciality = specialityCreReqDTO.toSpeciality();
        Speciality newSpeciality = specialityService.save(speciality);
        SpecialityResDTO specialityResDTO = newSpeciality.toSpecialityResDTO();

        return new ResponseEntity<>(specialityResDTO,HttpStatus.CREATED);
    }

}
