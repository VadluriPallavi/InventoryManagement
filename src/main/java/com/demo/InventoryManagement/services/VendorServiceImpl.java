package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.Vendor;
import com.demo.InventoryManagement.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> getVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendor(long vendorId) {
        return vendorRepository.findById(vendorId).get();
    }

    @Override
    public Vendor addVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return vendor;
    }

    @Override
    public void deleteVendor(long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).get();
        vendorRepository.delete(vendor);
    }
}
