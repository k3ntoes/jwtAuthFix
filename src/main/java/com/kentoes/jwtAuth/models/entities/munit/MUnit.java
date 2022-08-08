package com.kentoes.jwtAuth.models.entities.munit;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.kentoes.jwtAuth.models.entities.BaseEntity;
import com.kentoes.jwtAuth.models.entities.cabang.Cabang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "munits",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "unit"),
                @UniqueConstraint(columnNames = "name")
        })
@JsonPropertyOrder({"id", "unit", "name", "address", "cabang", "createdAt", "updatedAt", "createdBy", "updatedBy"})
public class MUnit extends BaseEntity<String> implements Serializable {
    @NaturalId
    @Size(max = 2)
    private String unit;
    @Size(max = 50)
    private String name;
    @Size(max = 100)
    private String address;
    @ManyToOne
    @JoinColumn(name = "satker", columnDefinition = "varchar(4)", referencedColumnName = "satker")
    private Cabang cabang;
}
