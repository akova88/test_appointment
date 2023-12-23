package com.cg.result.service.dm_gioitinh;

import com.cg.result.entity.DMGioiTinhDTO;
import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.repository.DM_GioiTinhRepository;
import com.cg.result.repository.IDMGioiTinh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DM_GioiTinhService implements IDM_GioiTinhService{

    @Autowired
    DM_GioiTinhRepository dmGioiTinhRepository;
    @Override
    public List<DM_GioiTinh> findAll() {
        return dmGioiTinhRepository.findAll();
    }

    @Override
    public List<DM_GioiTinh> getAllGioiTinh() {
        return dmGioiTinhRepository.getAllGioiTinh();
    }

    @Override
    public List<DM_GioiTinh> getAllGt() {
        List<IDMGioiTinh> idmGioiTinhs = dmGioiTinhRepository.getAllGt();
        List<DM_GioiTinh> dmGioiTinhs = idmGioiTinhs.stream().map(idmGioiTinh -> {
            DM_GioiTinh dmGioiTinh = new DM_GioiTinh();
            dmGioiTinh.setId(null);
            dmGioiTinh.setMa(idmGioiTinh.getMa());
            dmGioiTinh.setTenGioiTinh(idmGioiTinh.getTenGioiTinh());
            return dmGioiTinh;
        }).collect(Collectors.toList());

        return dmGioiTinhs;
    }

    @Override
    public List<DMGioiTinhDTO> getAllGt1() {
        return dmGioiTinhRepository.getAllGt1();
    }

    @Override
    public Optional<DM_GioiTinh> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public DM_GioiTinh save(DM_GioiTinh dmGioiTinh) {
        return null;
    }

    @Override
    public void delete(DM_GioiTinh dmGioiTinh) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
