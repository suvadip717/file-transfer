package com.api.scheme.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.scheme.entity.SchemeParticulars;
import com.api.scheme.service.SchemeParticularsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/scheme-particulars")
@Tag(name = "Scheme Particulars API", description = "Manage scheme particulars and offers")
public class SchemeParticularController {

    @Autowired
    private SchemeParticularsService schemeParticularsService;

    @Operation(summary = "Get all scheme particulars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched all scheme particulars")
    })
    @GetMapping
    public ResponseEntity<List<SchemeParticulars>> getAllSchemeParticulars() {
        List<SchemeParticulars> particulars = schemeParticularsService.getAllSchemeParticulars();
        return ResponseEntity.ok(particulars);
    }

    @Operation(summary = "Get a scheme particular by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme Particular found"),
            @ApiResponse(responseCode = "404", description = "Scheme Particular not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchemeParticularById(@PathVariable Long id) {
        Optional<SchemeParticulars> particular = schemeParticularsService.getSchemeParticularById(id);
        return particular.isPresent()
                ? ResponseEntity.ok(particular.get())
                : ResponseEntity.status(404).body("Scheme Particular not found with ID: " + id);
    }

    @GetMapping("/by-scheme/{schemeId}")
    @Operation(summary = "Get all scheme particulars by Scheme ID")
    public ResponseEntity<?> getSchemeParticularsBySchemeId(@PathVariable Long schemeId) {
        List<SchemeParticulars> particulars = schemeParticularsService.getParticularsBySchemeId(schemeId);
        if (particulars.isEmpty()) {
            return ResponseEntity.status(404).body("No Scheme Particulars found for Scheme ID: " + schemeId);
        }
        return ResponseEntity.ok(particulars);
    }

    @Operation(summary = "Create a new scheme particular")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme Particular created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<?> createSchemeParticular(@RequestBody SchemeParticulars schemeParticulars) {
        SchemeParticulars created = schemeParticularsService.createSchemeParticular(schemeParticulars);
        return ResponseEntity.ok("Scheme Particular created successfully with ID: " + created.getId());
    }

    @Operation(summary = "Update a scheme particular by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme Particular updated successfully"),
            @ApiResponse(responseCode = "404", description = "Scheme Particular not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchemeParticular(@PathVariable Long id,
            @RequestBody SchemeParticulars schemeParticulars) {
        try {
            SchemeParticulars updated = schemeParticularsService.updateSchemeParticular(id, schemeParticulars);
            return ResponseEntity.ok("Scheme Particular updated successfully with ID: " + updated.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete a scheme particular by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scheme Particular deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Scheme Particular not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchemeParticular(@PathVariable Long id) {
        try {
            schemeParticularsService.deleteSchemeParticular(id);
            return ResponseEntity.ok("Scheme Particular deleted successfully with ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Delete all scheme particulars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All scheme particulars deleted successfully")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteAllSchemeParticulars() {
        schemeParticularsService.deleteAllSchemeParticulars();
        return ResponseEntity.ok("All Scheme Particulars deleted successfully.");
    }
}
