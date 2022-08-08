package com.kentoes.jwtAuth.services.errLog;

import com.kentoes.jwtAuth.models.entities.errLog.ErrLog;

public interface ErrLogService {
    ErrLog save(ErrLog errLog);
}
