package com.kentoes.jwtAuth.services.cabang;

import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import com.kentoes.jwtAuth.models.entities.cabang.Cabang;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CabangService {
    Page<Cabang> findAll(FindAllReq findAllReq);

    Cabang findById(Long id);

    Cabang findBySatker(String satker);

    Cabang save(Cabang cabang);

    Long totData();

    void saveAll(List<Cabang> cabangs);
}
