package com.cg.model;

import com.cg.model.dtos.billTest.BillTestResDTO;
import com.cg.model.enums.EBookTime;
import com.cg.model.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bills_test")
public class BillTest extends BasePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_phieu", unique = true)
    private String maPhieu;

    @ManyToOne
    @JoinColumn(name = "dm_goi_id", referencedColumnName = "id", nullable = false)
    private DM_Goi dmGoi;

    @Column(name = "take_date", nullable = false)
    private Date takeDate;

    @Column(name = "time_book", nullable = false)
    @Enumerated(EnumType.STRING)
    private EBookTime timeBook;

    @ManyToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @Lob
    @Column(name = "reason_note", columnDefinition = "nvarchar(255)")
    private String reasonNote;

    @Column(precision = 10, scale = 0, nullable = false, updatable = false)
    private BigDecimal price;

    @Column(name = "home_service", columnDefinition = "bit default 0")
    private boolean homeService;

    @Column(name = "is_confirm", columnDefinition = "bit default 0")
    private boolean isConfirm;

    @Column(name = "staff_confirmed", columnDefinition = "nvarchar(255)")
    private String staffConfirmed;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public BillTest(String fullName, String email, EGender gender, String phone, Date DOB, String job, String identityNumber, String ethnic, Long id, String maPhieu, DM_Goi dmGoi, Date takeDate, EBookTime timeBook, LocationRegion locationRegion, String reasonNote, BigDecimal price, boolean homeService, boolean isConfirm, String staffConfirmed, User user) {
        super(fullName, email, gender, phone, DOB, job, identityNumber, ethnic);
        this.id = id;
        this.maPhieu = maPhieu;
        this.dmGoi = dmGoi;
        this.takeDate = takeDate;
        this.timeBook = timeBook;
        this.locationRegion = locationRegion;
        this.reasonNote = reasonNote;
        this.price = price;
        this.homeService = homeService;
        this.isConfirm = isConfirm;
        this.staffConfirmed = staffConfirmed;
        this.user = user;
    }

    public BillTestResDTO toBillTestResDTO() {
        return new BillTestResDTO()
                .setId(id)
                .setMaPhieu(maPhieu)
                .setDmGoi(dmGoi.toDmGoiResDTO())
                .setTakeDate(takeDate)
                .setTimeBookName(timeBook.name())
                .setFullName(getFullName())
                .setEmail(getEmail())
                .setGender(getGender())
                .setPhone(getPhone())
                .setDOB(getDOB())
                .setLocationRegion(locationRegion.toLocationRegionResDTO())
                .setReasonNote(reasonNote)
                .setPrice(dmGoi.getGiaGoi())
                .setHomeService(homeService)
                .setConfirm(isConfirm)
                .setStaffConfirm(staffConfirmed)
                .setUser(user.toUserResDTO());
    }

}
