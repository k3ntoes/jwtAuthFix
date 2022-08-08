package com.kentoes.jwtAuth.models.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FindAllReq implements Serializable {
    private int pos = 0;
    private int limit = 10;
    private String sortBy;
    private String sortDir;
    private String search;
}
