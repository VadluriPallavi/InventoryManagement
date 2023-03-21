package com.demo.InventoryManagement.controller;

import com.demo.InventoryManagement.entities.Vendor;
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
        List<Vendor> vendors = this.vendorService.getVendors();

        if (vendors.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(vendors));
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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/vendors/{vendorId}")
    public void deleteVendor(@PathVariable long vendorId) {
        this.vendorService.deleteVendor(vendorId);
    }
}
