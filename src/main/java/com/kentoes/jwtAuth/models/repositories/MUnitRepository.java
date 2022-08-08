package com.kentoes.jwtAuth.models.repositories;

import com.kentoes.jwtAuth.models.entities.munit.MUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MUnitRepository extends JpaRepository<MUnit, Long> {
    @EntityGraph(attributePaths = {"cabang"})
    @Query("SELECT u FROM MUnit u " +
            "WHERE u.unit=?1" +
            "OR u.name LIKE CONCAT('%',?1,'%')")
    Page<MUnit> findAllPage(String search, Pageable build);

    @EntityGraph(attributePaths = {"cabang"})
    Optional<MUnit> findByUnit(String unit);

    @EntityGraph(attributePaths = {"cabang"})
    Page<MUnit> findByCabang_Satker(String search, Pageable build);
}