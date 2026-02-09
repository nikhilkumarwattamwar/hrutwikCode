//package com.loanapp.loanManagementSystem.contollers.homeloan;
//
//import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsRequestDto;
//import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsResponseDto;
//import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
//import com.loanapp.loanManagementSystem.service.homeloan.service.PropertyDetailsService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class PropertyDetailsController {
//
//    private final PropertyDetailsService propertyDetailsService;
//
//    @PostMapping("/home-loans/{homeLoanId}/property")
//    public ResponseEntity<PropertyDetails> addProperty(
//            @PathVariable Long homeLoanId,
//            @RequestBody PropertyDetailsRequestDto dto) {
//        return ResponseEntity.ok(
//                propertyDetailsService.addPropertyDetails(homeLoanId, dto)
//        );
//    }
//
//    @GetMapping("/properties/{propertyId}")
//    public ResponseEntity<PropertyDetailsResponseDto> getById(@PathVariable Long propertyId) {
//        return ResponseEntity.ok(
//                propertyDetailsService.getPropertyById(propertyId)
//        );
//    }
//
//    @GetMapping("/home-loans/{homeLoanId}/property")
//    public ResponseEntity<PropertyDetails> getByHomeLoan(
//            @PathVariable Long homeLoanId) {
//        return ResponseEntity.ok(
//                propertyDetailsService.getPropertyByHomeLoanId(homeLoanId)
//        );
//    }
//
//    @PutMapping("/properties/{propertyId}")
//    public ResponseEntity<PropertyDetails> updateProperty(
//            @PathVariable Long propertyId,
//            @RequestBody PropertyDetailsRequestDto dto) {
//        return ResponseEntity.ok(
//                propertyDetailsService.updateProperty(propertyId, dto)
//        );
//    }
//
//    @DeleteMapping("/properties/{propertyId}")
//    public ResponseEntity<String> deleteProperty(
//            @PathVariable Long propertyId) {
//        propertyDetailsService.deleteProperty(propertyId);
//        return ResponseEntity.ok("Property deleted successfully");
//    }
//}
//
//
package com.loanapp.loanManagementSystem.contollers.homeloan;

import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsRequestDto;
import com.loanapp.loanManagementSystem.dto.homeloandto.PropertyDetailsResponseDto;
import com.loanapp.loanManagementSystem.entities.loan.homeLoan.PropertyDetails;
import com.loanapp.loanManagementSystem.service.homeloan.service.PropertyDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PropertyDetailsController {

    private final PropertyDetailsService propertyDetailsService;


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/home-loans/{homeLoanId}/property")
    public ResponseEntity<PropertyDetails> addProperty(
            @PathVariable Long homeLoanId,
            @RequestBody PropertyDetailsRequestDto dto) {

        log.info("Add property request received for HomeLoan ID: {}", homeLoanId);

        PropertyDetails property =
                propertyDetailsService.addPropertyDetails(homeLoanId, dto);

        log.info("Property added successfully. Property ID: {}",
                property.getPropertyId());

        return ResponseEntity
                .status(201)
                .body(property);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDetailsResponseDto> getById(
            @PathVariable Long propertyId) {

        log.info("Fetch property request. Property ID: {}", propertyId);

        PropertyDetailsResponseDto response =
                propertyDetailsService.getPropertyById(propertyId);

        return ResponseEntity.ok(response);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/home-loans/{homeLoanId}/property")
    public ResponseEntity<PropertyDetails> getByHomeLoan(
            @PathVariable Long homeLoanId) {

        log.info("Fetch property by HomeLoan ID: {}", homeLoanId);

        PropertyDetails property =
                propertyDetailsService.getPropertyByHomeLoanId(homeLoanId);

        return ResponseEntity.ok(property);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDetails> updateProperty(
            @PathVariable Long propertyId,
            @RequestBody PropertyDetailsRequestDto dto) {

        log.info("Update property request. Property ID: {}", propertyId);

        PropertyDetails updated =
                propertyDetailsService.updateProperty(propertyId, dto);

        log.info("Property updated successfully. Property ID: {}", propertyId);

        return ResponseEntity.ok(updated);
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<Void> deleteProperty(
            @PathVariable Long propertyId) {

        log.info("Delete property request. Property ID: {}", propertyId);

        propertyDetailsService.deleteProperty(propertyId);

        log.info("Property deleted successfully. Property ID: {}", propertyId);

        return ResponseEntity.noContent().build();
    }
}
