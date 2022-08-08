package com.kentoes.jwtAuth.models.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
public class SaveResult {
    private int code = HttpStatus.CREATED.value();
    private HttpStatus status = HttpStatus.CREATED;
    private String id;
    private String message = "Data berhasil disimpan!";

    public SaveResult(String id) {
        this.id = id;
    }

    public SaveResult(Long id) {
        this.id = String.valueOf(id);
    }

    public ResponseEntity<?> build(String id) {
        HttpStatus status = HttpStatus.CREATED;
        return ResponseEntity.status(status).body(new SaveResult(id));
    }

    public ResponseEntity<?> build(Long id) {
        HttpStatus status = HttpStatus.CREATED;
        return ResponseEntity.status(status).body(new SaveResult(id));
    }
}

