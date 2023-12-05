package com.cg.service.dm_nhom_dv;

import com.cg.model.DMNhomDV;
import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
import com.cg.repository.DM_NhomDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DMNhomDvService implements IDMNhomDvService{

    @Autowired
    private DM_NhomDVRepository dmNhomDVRepository;

    @Override
    public List<DMNhomDV> findAll() {
        return dmNhomDVRepository.findAll();
    }

    @Override
    public List<DMNhomDvResDTO> findAllDmNhomDvResDTO() {
        return dmNhomDVRepository.findAllDmNhomDvResDTO();
    }

    @Override
    public Optional<DMNhomDV> findById(Long id) {
        return dmNhomDVRepository.findById(id);
    }

    @Override
    public DMNhomDV findDMNhomDVByMaNhomDv(String maNhomDmDichVu) {
        return dmNhomDVRepository.findDMNhomDVByMaNhomDv(maNhomDmDichVu);
    }

    @Override
    public DMNhomDV save(DMNhomDV dmNhomDV) {
        return null;
    }

    @Override
    public void delete(DMNhomDV dmNhomDV) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
