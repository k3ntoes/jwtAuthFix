package com.kentoes.jwtAuth.models.entities.cabang;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.jwtAuth.models.entities.BaseEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cabangs", uniqueConstraints = {
        @UniqueConstraint(columnNames = "satker"),
        @UniqueConstraint(columnNames = "name")})
@JsonPropertyOrder({"id", "satker", "name", "address", "createdAt", "updatedAt", "createdBy", "updatedBy"})
public class Cabang extends BaseEntity<String> implements Serializable {
    @NaturalId
    @Size(max = 4)
    private String satker;
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;
}
