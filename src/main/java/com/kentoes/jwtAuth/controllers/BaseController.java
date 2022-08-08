package com.kentoes.jwtAuth.controllers;

import com.kentoes.jwtAuth.models.dto.response.ErrorResult;
import com.kentoes.jwtAuth.models.dto.response.SaveResult;
import com.kentoes.jwtAuth.models.dto.response.SingleResult;

public abstract class BaseController {
    public SingleResult singleResult = new SingleResult<>();
    public ErrorResult errorResult = new ErrorResult();
    public SaveResult saveResult = new SaveResult();
}
