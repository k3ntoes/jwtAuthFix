package com.kentoes.jwtAuth.services.munit;

import com.kentoes.jwtAuth.helper.PageableBuilder;
import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import com.kentoes.jwtAuth.models.entities.munit.MUnit;
import com.kentoes.jwtAuth.models.repositories.MUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MUnitServiceImpl implements MUnitService {
    @Autowired
    private MUnitRepository repository;

    @Override
    public Page<MUnit> findAll(FindAllReq findAllReq) {
        return repository.findAllPage(findAllReq.getSearch(), PageableBuilder.build(findAllReq));
    }

    @Override
    public MUnit findById(Long id) {
        Optional<MUnit> mUnit = repository.findById(id);
        return mUnit.orElse(null);
    }

    @Override
    public MUnit findByUnit(String unit) {
        Optional<MUnit> mUnit = repository.findByUnit(unit);
        return mUnit.orElse(null);
    }

    @Override
    public Page<MUnit> findBySatker(FindAllReq findAllReq) {
        return repository.findByCabang_Satker(findAllReq.getSearch(), PageableBuilder.build(findAllReq));
    }

    @Override
    public MUnit save(MUnit mUnit) {
        return repository.save(mUnit);
    }

    @Override
    public Long totData() {
        return repository.count();
    }

    @Override
    public void saveAll(List<MUnit> mUnits) {
        repository.saveAll(mUnits);
    }
}
