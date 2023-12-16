package com.cg.service.billTest;

import com.cg.model.BillTest;
import com.cg.model.DM_Goi;
import com.cg.model.LocationRegion;
import com.cg.model.dtos.billTest.BillTestResDTO;
import com.cg.repository.BillTestRepository;
import com.cg.repository.DM_GoiRepository;
import com.cg.repository.LocationRegionRepository;
import com.cg.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillTestService implements IBillTestService{

    @Autowired
    private BillTestRepository billTestRepository;

    @Autowired
    private LocationRegionRepository locationRegionRepository;


    @Override
    public List<BillTest> findAll() {
        return billTestRepository.findAll();
    }

    @Override
    public List<BillTestResDTO> findAllBillTestResDTO() {
        return billTestRepository.findAllBillTestResDTO();
    }

    @Override
    public Optional<BillTest> findById(Long id) {
        return billTestRepository.findById(id);
    }

    @Override
    public BillTest save(BillTest billTest) {
        return billTestRepository.save(billTest);
    }

    @Override
    public BillTest create(BillTest billTest, LocationRegion locationRegion) {
        LocationRegion locationRegion1 = locationRegionRepository.save(locationRegion);
        billTest.setLocationRegion(locationRegion1);

        String takeDateStr = DateFormat.format(billTest.getTakeDate()).replace("/","");
        String maPhieu = "XN"+takeDateStr+locationRegion1.getId();
        billTest.setMaPhieu(maPhieu);

        return billTestRepository.save(billTest);
    }

    @Override
    public void delete(BillTest billTest) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
