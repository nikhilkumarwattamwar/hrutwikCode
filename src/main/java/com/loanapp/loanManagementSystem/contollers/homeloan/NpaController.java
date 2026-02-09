package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.dto.homeloandto.ApiResponse;
import com.loanapp.loanManagementSystem.dto.homeloandto.HomeLoanNpaResponseDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.NpaStatusUpdateRequestDto;
import com.loanapp.loanManagementSystem.service.homeloan.service.NpaService;
import com.loanapp.loanManagementSystem.service.homeloan.service.impl.NpaServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/npa")
public class NpaController {

    private final NpaService npaService;

    public NpaController(NpaService npaService) {
        this.npaService = npaService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse<List<HomeLoanNpaResponseDto>>> getAllNpas() {

        List<HomeLoanNpaResponseDto> npas = npaService.getAllNpas();

        return ResponseEntity.ok(
                ApiResponse.success("NPA list fetched successfully", npas)
        );
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{npaId}")
    public ResponseEntity<ApiResponse<HomeLoanNpaResponseDto>> getNpa(
            @PathVariable Long npaId) {

        HomeLoanNpaResponseDto npa = npaService.getNpa(npaId);

        return ResponseEntity.ok(
                ApiResponse.success("NPA details fetched successfully", npa)
        );
    }


    @PutMapping("/{npaId}/status")
    public ResponseEntity<ApiResponse<HomeLoanNpaResponseDto>> updateStatus(
            @PathVariable Long npaId,
            @RequestBody NpaStatusUpdateRequestDto request) {

        HomeLoanNpaResponseDto updated =
                npaService.updateStatus(npaId, request.getStatus());

        return ResponseEntity.ok(
                ApiResponse.success("NPA status updated successfully", updated)
        );
    }


    @PostMapping("/{npaId}/cure")
    public ResponseEntity<ApiResponse<HomeLoanNpaResponseDto>> cure(
            @PathVariable Long npaId) {

        HomeLoanNpaResponseDto cured = npaService.cureNpa(npaId);

        return ResponseEntity.ok(
                ApiResponse.success("NPA cured successfully", cured)
        );
    }
}
