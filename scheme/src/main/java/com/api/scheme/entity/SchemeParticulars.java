package com.api.scheme.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblscheme_particulars")
public class SchemeParticulars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "scheme_id", referencedColumnName = "schemeId", nullable = false, updatable = false, insertable = false)
    @JsonBackReference
    private Scheme scheme;

    @Column(name = "scheme_id")
    private Long schemeId;

    private Long groupId;
    private String particulars;
    private String groupName;
    private Long productId;
    private Long minQty;
    private String unit;
    private Long freeQty;
    private String unitFree;
    private Long productIdFree;
}
