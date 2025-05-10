package com.api.scheme.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tblscheme")
public class Scheme {

    @Id
    @Column(nullable = false, unique = true)
    private Long schemeId;
    private String schemeName;
    private String schemeType;
    private String applicableOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(description = "Scheme start date and time in format yyyy-MM-dd HH:mm", example = "2025-04-29 09:30")
    private LocalDateTime startDate;
    @Schema(description = "Scheme end date and time in format yyyy-MM-dd HH:mm", example = "2025-04-29 18:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "schemeId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SchemeParticulars> schemeParticulars;

}
