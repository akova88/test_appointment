package com.cg.result.service;

import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.repository.DM_GioiTinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
