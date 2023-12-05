package com.cg.service.dm_goi;

import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.repository.DM_GoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DMGoiService implements IDMGoiService{
    @Autowired
    private DM_GoiRepository dmGoiRepository;

    @Override
    public List<DM_Goi> findAll() {
        return dmGoiRepository.findAll();
    }

//    @Override
//    public List<DM_GoiResDTO> findAllDmGoiResDTO() {
//        return dmGoiRepository.findAllDmGoiResDTO();
//    }

    @Override
    public Optional<DM_Goi> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DM_Goi save(DM_Goi dmGoi) {
        return null;
    }

    @Override
    public void delete(DM_Goi dmGoi) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
