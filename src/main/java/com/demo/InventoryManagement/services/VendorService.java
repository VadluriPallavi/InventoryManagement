package com.demo.InventoryManagement.services;

import com.demo.InventoryManagement.entities.Vendor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface VendorService {
    public List<Vendor> getVendors();

    public Vendor getVendor(long vendorId);

    public Vendor addVendor(Vendor vendor);

    public void deleteVendor( long vendorId);
}
