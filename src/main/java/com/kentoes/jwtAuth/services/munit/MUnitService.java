package com.kentoes.jwtAuth.services.munit;

import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import com.kentoes.jwtAuth.models.entities.munit.MUnit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MUnitService {
    Page<MUnit> findAll(FindAllReq findAllReq);

    MUnit findById(Long id);

    MUnit findByUnit(String unit);

    Page<MUnit> findBySatker(FindAllReq findAllReq);

    MUnit save(MUnit mUnit);
    Long totData();

    void saveAll(List<MUnit> mUnits);
}
