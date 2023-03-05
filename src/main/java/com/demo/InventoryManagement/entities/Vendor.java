package com.demo.InventoryManagement.entities;

public class Vendor {
	private long vendorId;
	private String vendorName;
	private String vendorLink;
	
	

	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Vendor(long vendorId, String vendorName, String vendorLink) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorLink = vendorLink;
	}

	
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorLink() {
		return vendorLink;
	}
	public void setVendorLink(String vendorLink) {
		this.vendorLink = vendorLink;
	}
	
	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorLink=" + vendorLink + "]";
	}
}
