package com.cg.model.dtos.locationRegion;

import com.cg.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationRegionUpReqDTO {

    @NotNull(message = "ID tỉnh không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID tỉnh")
    @NotEmpty(message = "ID tỉnh không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID tỉnh phải là một số")
    private String provinceId;

    @NotNull(message = "Tên tỉnh không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Tên tỉnh")
    @NotEmpty(message = "Tên tỉnh không thể để trống")
    private String provinceName;

    @NotNull(message = "ID thành phố không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID thành phố")
    @NotEmpty(message = "ID thành phố không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID thành phố phải là một số")
    private String districtId;

    @NotNull(message = "Tên thành phố không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Tên thành phố")
    @NotEmpty(message = "Tên thành phố không thể để trống")
    private String districtName;

    @NotNull(message = "ID phường không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID phường")
    @NotEmpty(message = "ID phường không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID phường phải là một số")
    private String wardId;

    @NotNull(message = "Tên phường không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Tên phường")
    @NotEmpty(message = "Tên phường không thể để trống")
    private String wardName;

    @NotNull(message = "Địa chỉ không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Địa chỉ")
    @NotEmpty(message = "Địa chỉ không thể để trống")
    @Size(min = 3, max = 50, message = "Địa chỉ phải từ ")
    private String address;

    public LocationRegion toLocationRegion(Long locationRegionId) {
        return new LocationRegion()
                .setId(locationRegionId)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address)
                ;
    }
}
