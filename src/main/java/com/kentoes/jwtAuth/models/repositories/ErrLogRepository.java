package com.kentoes.jwtAuth.models.repositories;

import com.kentoes.jwtAuth.models.entities.errLog.ErrLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ErrLogRepository extends JpaRepository<ErrLog, Long> {
    Optional<ErrLog> findByErrorByAndErrorClassAndErrorLogAndErrorMethod(String errorBy, String errorClass, String errorLog, String errorMethod);

    @Query(value = "SELECT PASSWORD(?1)", nativeQuery = true)
    Optional<String> mysqlPass(String password);
}