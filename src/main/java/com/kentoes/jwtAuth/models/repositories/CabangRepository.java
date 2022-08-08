package com.kentoes.jwtAuth.models.repositories;

import com.kentoes.jwtAuth.models.entities.cabang.Cabang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CabangRepository extends JpaRepository<Cabang, Long> {
    @Query("SELECT c FROM Cabang c " +
            "WHERE c.satker=?1 " +
            "OR c.name LIKE CONCAT('%',?1,'%')")
    Page<Cabang> findAllPage(String search, Pageable pageable);

    Optional<Cabang> findBySatker(String satker);
}