package com.cg.service.dm_dv;

import com.cg.exception.DataInputException;
import com.cg.model.DMDichVu;
import com.cg.model.DMNhomDV;
import com.cg.model.dtos.dmDichVu.DMDichVuReqDTO;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.repository.DM_DichVuRepository;
import com.cg.repository.DM_NhomDVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DMDichVuService implements IDMDichVuService{

    @Autowired
    private DM_DichVuRepository dmDichVuRepository;

    @Autowired
    private DM_NhomDVRepository dmNhomDVRepository;

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List<DMDichVuResDTO> findAllDMDichVuResDTO() {
        return dmDichVuRepository.findAllDMDichVuResDTO();
    }

    @Override
    public Optional<DMDichVu> findById(Long id) {
        return dmDichVuRepository.findById(id);
    }

    @Override
    public DMDichVu findDMDichVuByMaDv(String maDv) {
        return dmDichVuRepository.findDMDichVuByMaDv(maDv);
    }

    @Override
    public Boolean existsDMDichVuByTenDv(String tenDv) {
        return dmDichVuRepository.existsDMDichVuByTenDv(tenDv);
    }

    @Override
    public Boolean existsDMDichVuByTenDvAndAndIdNot(String tenDv, Long id) {
        return existsDMDichVuByTenDvAndAndIdNot(tenDv, id);
    }

    @Override
    public DMDichVuResDTO create(DMDichVuReqDTO dmDichVuReqDTO) {
        String maNhomDv = dmDichVuReqDTO.getMaNhomDv();

        DMNhomDV dmNhomDV = dmNhomDVRepository.findDMNhomDVByMaNhomDv(maNhomDv);
        if (dmNhomDV == null) {
            throw new DataInputException("Mã nhóm dịch vụ không tồn tại");
        }

        DMDichVu dmDichVu = dmDichVuReqDTO.toDmDichVu(dmNhomDV);
        dmDichVuRepository.save(dmDichVu);
        return dmDichVu.toDmDichVuResDTO();
    }

    @Override
    public DMDichVu save(DMDichVu dmDichVu) {
        return dmDichVuRepository.save(dmDichVu);
    }

    @Override
    public void delete(DMDichVu dmDichVu) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
