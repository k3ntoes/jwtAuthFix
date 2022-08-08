package com.kentoes.jwtAuth.utils;

import com.kentoes.jwtAuth.models.entities.cabang.Cabang;
import com.kentoes.jwtAuth.models.entities.enums.ERole;
import com.kentoes.jwtAuth.models.entities.munit.MUnit;
import com.kentoes.jwtAuth.models.entities.role.Role;
import com.kentoes.jwtAuth.services.cabang.CabangService;
import com.kentoes.jwtAuth.services.munit.MUnitService;
import com.kentoes.jwtAuth.services.role.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class initApp implements CommandLineRunner {
    @Autowired
    private CabangService cabangService;
    @Autowired
    private MUnitService mUnitService;
    @Autowired
    private RoleService roleService;

    @Override
    public void run(String... args) {
        if (roleService.totData() == 0)
            masterRole();
        if (cabangService.totData() == 0)
            masterCabang();
        if (mUnitService.totData() == 0)
            masterUnit();
    }

    private void masterRole() {
        List<Role> roles = Stream.of(
                new Role(ERole.ROLE_ADMIN),
                new Role(ERole.ROLE_CATER),
                new Role(ERole.ROLE_CHECKER_TI),
                new Role(ERole.ROLE_CHECKER_CABANG),
                new Role(ERole.ROLE_CHECKER_KOPERASI),
                new Role(ERole.ROLE_IDLE)
        ).collect(Collectors.toList());
        roleService.saveAll(roles);
    }


    private void masterCabang() {
        List<Cabang> cabangs = Stream.of(
                new Cabang("0100", "Kantor Pusat", "Purwokerto"),
                new Cabang("0200", "Cabang Purwokerto 1", "Purwokerto"),
                new Cabang("0300", "Cabang Purwokerto 2", "Purwokerto"),
                new Cabang("0400", "Cabang Banyumas", "Banyumas"),
                new Cabang("0500", "Cabang Wangon", "Wangon"),
                new Cabang("0600", "Cabang Ajibarang", "Ajibarang"),
                new Cabang("0700", "AMDK", "Purwokerto")
        ).collect(Collectors.toList());
        cabangService.saveAll(cabangs);
        try {
            Thread.sleep(2000);
            Cabang cabang = cabangService.findBySatker("0200");
            cabang.setName("Cabang Purwokerto 1 Edited");
            cabangService.save(cabang);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }

    private void masterUnit() {
        List<MUnit> mUnits = Stream.of(
                new MUnit("01", "Purwokerto 1", "-", cabangService.findBySatker("0200")),
                new MUnit("02", "Gerilya Selatan", "-", cabangService.findBySatker("0300")),
                new MUnit("03", "Purwokerto 2", "-", cabangService.findBySatker("0300")),
                new MUnit("04", "Dukuhwaluh", "-", cabangService.findBySatker("0400")),
                new MUnit("05", "Banyumas", "-", cabangService.findBySatker("0400")),
                new MUnit("06", "Sokaraja", "-", cabangService.findBySatker("0400")),
                new MUnit("07", "Ajibarang", "-", cabangService.findBySatker("0600")),
                new MUnit("08", "Kalibagor", "-", cabangService.findBySatker("0400")),
                new MUnit("09", "Wangon", "-", cabangService.findBySatker("0500")),
                new MUnit("10", "Pos Pel.Baturaden", "-", cabangService.findBySatker("0200")),
                new MUnit("11", "Cilongok", "-", cabangService.findBySatker("0600")),
                new MUnit("12", "Purwojati", "-", cabangService.findBySatker("0500")),
                new MUnit("13", "Patikraja", "-", cabangService.findBySatker("0300")),
                new MUnit("14", "Kemranjen", "-", cabangService.findBySatker("0400")),
                new MUnit("15", "Kembaran", "-", cabangService.findBySatker("0400")),
                new MUnit("16", "Sumbang", "-", cabangService.findBySatker("0400")),
                new MUnit("17", "Teluk", "-", cabangService.findBySatker("0300")),
                new MUnit("18", "Tiara Permai", "-", cabangService.findBySatker("0300")),
                new MUnit("19", "Rempoah", "-", cabangService.findBySatker("0200"))
        ).collect(Collectors.toList());
        mUnitService.saveAll(mUnits);
        MUnit mUnit = mUnitService.findByUnit("19");
        mUnit.setName("Rempoah Edited");
        mUnitService.save(mUnit);
    }


}
