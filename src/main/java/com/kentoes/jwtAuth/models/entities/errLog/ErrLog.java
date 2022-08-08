package com.kentoes.jwtAuth.models.entities.errLog;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "error_logs")
@JsonPropertyOrder({"id", "errorBy", "errorType", "errorLog", "errorClass"})
public class ErrLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String errorBy;
    private String errorType;
    private String errorLog;
    private String errorClass;
    private String errorMethod;
    private Integer errCount = 1;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ErrLog(String errorBy, String errorType, String errorLog, String errorClass, String errorMethod) {
        this.errorBy = errorBy;
        this.errorType = errorType;
        this.errorLog = errorLog;
        this.errorClass = errorClass;
        this.errorMethod = errorMethod;
    }

    public void appendCount() {
        this.errCount++;
    }
}
