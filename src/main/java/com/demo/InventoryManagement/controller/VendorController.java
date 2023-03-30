package com.demo.InventoryManagement.controller;

import com.demo.InventoryManagement.entities.Vendor;
import com.demo.InventoryManagement.exceptions.CustomErrorException;
import com.demo.InventoryManagement.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class VendorController {

    @Autowired
    private VendorService vendorService;


    @GetMapping("/vendors")
    public ResponseEntity<List<Vendor>> getVendors() {
        try {
            List<Vendor> vendors = this.vendorService.getVendors();
            if (vendors.size() == 0) {
                throw new CustomErrorException(
                        HttpStatus.NOT_FOUND,
                        "There are no vendors found"
                );
            }
            return ResponseEntity.of(Optional.of(vendors));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(
                    HttpStatus.NOT_FOUND,
                    "There are no vendors found"
            );
        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error in fetching vendors list"
            );
        }
    }

    @GetMapping("/vendors/{vendorId}")
    public ResponseEntity<Vendor> getVendor(@PathVariable long vendorId) {
        Vendor vendor = this.vendorService.getVendor(vendorId);
        if (vendor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(vendor));
    }

    @PostMapping("/vendors")
    public ResponseEntity<Vendor> addVendor(@RequestBody Vendor vendor) {
        Vendor addedVendor = null;
        try {
            addedVendor = this.vendorService.addVendor(vendor);
            return ResponseEntity.of(Optional.of(addedVendor));
        } catch (Exception e) {
            throw new CustomErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error in adding new vendor",
                    vendor
            );
        }

    }

    @DeleteMapping("/vendors/{vendorId}")
    public void deleteVendor(@PathVariable long vendorId) {
        this.vendorService.deleteVendor(vendorId);
    }
}
