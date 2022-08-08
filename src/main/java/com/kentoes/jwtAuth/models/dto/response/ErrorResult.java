package com.kentoes.jwtAuth.models.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResult {
    private Integer code;
    private HttpStatus status;
    private List<String> errorMessage = new ArrayList<>();

    private void addMessage(String message) {
        this.errorMessage.add(message);
    }

    public ErrorResult(HttpStatus status) {
        this.code = status.value();
        this.status = status;
    }

    public ResponseEntity<?> build(String message) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResult result = new ErrorResult(status);
        result.addMessage(message);
        return ResponseEntity.status(status).body(result);
    }

    public ResponseEntity<?> build(String message, HttpStatus status) {
        ErrorResult result = new ErrorResult(status);
        result.addMessage(message);
        return ResponseEntity.status(status).body(result);
    }

    public ResponseEntity<?> build(Errors errors) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResult result = new ErrorResult(status);
        errors.getAllErrors().forEach(objectError -> {
            result.addMessage(objectError.getDefaultMessage());
        });
        return ResponseEntity.status(status).body(result);
    }
}
