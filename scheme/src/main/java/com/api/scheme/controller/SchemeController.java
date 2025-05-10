package com.api.scheme.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.scheme.entity.Scheme;
import com.api.scheme.repository.SchemeRepository;
import com.api.scheme.service.SchemeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/schemes")
@Tag(name = "Scheme API", description = "Endpoints for managing schemes")
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    @Autowired
    private SchemeRepository schemeRepository;

    @Operation(summary = "Get all schemes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Schemes retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<?> getAllSchemes() {
        List<Scheme> schemes = schemeService.getAllSchemes();
        if (schemes.isEmpty()) {
            return ResponseEntity.ok("No schemes available");
        }
        return ResponseEntity.ok(schemes);
    }

    @Operation(summary = "Get a scheme by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme found"),
            @ApiResponse(responseCode = "404", description = "Scheme not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchemeById(@PathVariable Long id) {
        Optional<Scheme> scheme = schemeService.getSchemeById(id);
        if (scheme.isPresent()) {
            return ResponseEntity.ok(scheme.get());
        } else {
            return ResponseEntity.status(404).body("Scheme not found with ID: " + id);
        }
        
    }

    @Operation(summary = "Create a new scheme")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
            @ApiResponse(responseCode = "409", description = "Scheme ID already exists")
    })
    @PostMapping
    public ResponseEntity<?> createScheme(@RequestBody Scheme scheme) {
        Optional<Scheme> existingScheme = schemeRepository.findById(scheme.getSchemeId());

        if (existingScheme.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Scheme with ID " + scheme.getSchemeId() + " already exists.");
        }

        Scheme createdScheme = schemeService.createScheme(scheme);

        return ResponseEntity.ok("Scheme created successfully with ID: " + createdScheme.getSchemeId());
    }

    @Operation(summary = "Update a scheme by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scheme not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScheme(@PathVariable Long id, @RequestBody Scheme updatedScheme) {
        try {
            Scheme scheme = schemeService.updateScheme(id, updatedScheme);
            return ResponseEntity.ok("Scheme updated successfully with ID: " + scheme.getSchemeId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Scheme not found with ID: " + id);
        }
    }

    @Operation(summary = "Delete a scheme by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Scheme not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScheme(@PathVariable Long id) {
        try {
            schemeService.deleteScheme(id);
            return ResponseEntity.ok("Scheme deleted successfully with ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Scheme not found with ID: " + id);
        }
    }
}
