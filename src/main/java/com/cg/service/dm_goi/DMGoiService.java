package com.cg.service.dm_goi;

import com.cg.model.DMDichVu;
import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiReqDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.repository.DM_DichVuRepository;
import com.cg.repository.DM_GoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DMGoiService implements IDMGoiService{
    @Autowired
    private DM_GoiRepository dmGoiRepository;

    @Autowired
    private DM_DichVuRepository dmDichVuRepository;

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
        return dmGoiRepository.findById(id);
    }

    @Override
    public Boolean existsDM_GoiByMaGoiOrTenGoi(String maGoi, String tenGoi) {
        return dmGoiRepository.existsDM_GoiByMaGoiOrTenGoi(maGoi, tenGoi);
    }

    @Override
    public DM_GoiResDTO create(DM_GoiReqDTO dmGoiReqDTO) throws JSONException {
        List<DMDichVu> dmDichVuList = new ArrayList<>();
        String maDVlistStr = dmGoiReqDTO.getMaDvList();

        if (maDVlistStr != null && !maDVlistStr.isEmpty()) {
            JSONArray maDVlist = new JSONArray(maDVlistStr);

            for (int i =0; i<maDVlist.length(); i++) {
                String maDv = maDVlist.getString(i);
                DMDichVu dmDichVu = dmDichVuRepository.findDMDichVuByMaDv(maDv);
                dmDichVuList.add(dmDichVu);
            }
        }

        DM_Goi dmGoi = dmGoiReqDTO.toDmGoi(dmDichVuList);
        dmGoiRepository.save(dmGoi);

        return dmGoi.toDmGoiResDTO();
    }

    @Override
    public DM_Goi save(DM_Goi dmGoi) {
        return dmGoiRepository.save(dmGoi);
    }

    @Override
    public void delete(DM_Goi dmGoi) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
