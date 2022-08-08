package com.kentoes.jwtAuth.services.errLog;

import com.kentoes.jwtAuth.models.entities.errLog.ErrLog;
import com.kentoes.jwtAuth.models.repositories.ErrLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ErrLogServiceImpl implements ErrLogService {
    @Autowired
    private ErrLogRepository repository;

    @Override
    public ErrLog save(ErrLog errLog) {
        Optional<ErrLog> find = repository
                .findByErrorByAndErrorClassAndErrorLogAndErrorMethod(
                        errLog.getErrorBy(),
                        errLog.getErrorClass(),
                        errLog.getErrorLog(),
                        errLog.getErrorMethod());
        if (find.isPresent()) {
            errLog.setId(find.get().getId());
            errLog.setErrCount(find.get().getErrCount());
            errLog.appendCount();
            return repository.save(errLog);
        }
        return repository.save(errLog);
    }
}
