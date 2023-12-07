package com.cg.model.dtos.dm_goi;

import com.cg.model.DMDichVu;
import com.cg.model.DM_Goi;
import com.cg.model.dtos.dmDichVu.DMDichVuReqDTO;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DM_GoiReqDTO {

    @NotBlank(message = "Mã gói xét nghiệm là bắt buộc")
    private String maGoi;

    @NotBlank(message = "Tên gói xét nghiệm không được để trống")
    @Size(min = 5, max = 150, message = "Tên phải từ 5 - 150 ký tự")
    private String tenGoi;

    @NotNull(message = "Giá dịch vụ là bắt buộc")
    @DecimalMin(value = "0.01", message = "Giá dịch vụ phải là định dạng số lớn hơn 0")
    private String giaGoi;

    private String maDvList;

    public DM_Goi toDmGoi(List<DMDichVu> dmDichVuList) {
        return new DM_Goi()
                .setId(null)
                .setMaGoi(maGoi)
                .setTenGoi(tenGoi)
                .setGiaGoi(BigDecimal.valueOf(Long.parseLong(giaGoi)))
                .setDmDichVuList(dmDichVuList);
    }
}
