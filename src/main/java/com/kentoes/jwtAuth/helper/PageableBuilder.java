package com.kentoes.jwtAuth.helper;

import com.kentoes.jwtAuth.models.dto.request.FindAllReq;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageableBuilder {
    public static Pageable build(FindAllReq req) {
        if (Objects.nonNull(req.getSortBy())) {
            if (req.getSortDir().equals("desc"))
                return PageRequest.of(req.getPos(), req.getLimit(), Sort.by(Sort.Direction.DESC, req.getSortBy()));
            return PageRequest.of(req.getPos(), req.getLimit(), Sort.by(Sort.Direction.ASC, req.getSortBy()));
        }
        return PageRequest.of(req.getPos(), req.getLimit());
    }
}
