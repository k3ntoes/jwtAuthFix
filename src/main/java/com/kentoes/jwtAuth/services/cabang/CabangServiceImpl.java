package com.kentoes.jwtAuth.services.cabang;

import com.kentoes.jwtAuth.helper.PageableBuilder;
import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import com.kentoes.jwtAuth.models.entities.cabang.Cabang;
import com.kentoes.jwtAuth.models.repositories.CabangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabangServiceImpl implements CabangService {
    @Autowired
    private CabangRepository repository;

    @Override
    public Page<Cabang> findAll(FindAllReq findAllReq) {
        return repository.findAllPage(findAllReq.getSearch(), PageableBuilder.build(findAllReq));
    }

    @Override
    public Cabang findById(Long id) {
        Optional<Cabang> cabang = repository.findById(id);
        return cabang.orElse(null);
    }

    @Override
    public Cabang findBySatker(String satker) {
        Optional<Cabang> cabang = repository.findBySatker(satker);
        return cabang.orElse(null);
    }

    @Override
    public Cabang save(Cabang cabang) {
        return repository.save(cabang);
    }

    @Override
    public Long totData() {
        return repository.count();
    }

    @Override
    public void saveAll(List<Cabang> cabangs) {
        repository.saveAll(cabangs);
    }
}
