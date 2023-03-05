package com.demo.InventoryManagement.entities;

public class Item {
	private long itemId;
	private String itemName;
	private long categoryId;
	private long pricePerUnit;
	private long quantity;
	private long vendorId;
	private long shelfNo;
	
	
	
	public Item(long itemId, String itemName, long categoryId, long pricePerUnit, long quantity, long vendorId,
			long shelfNo) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.categoryId = categoryId;
		this.pricePerUnit = pricePerUnit;
		this.quantity = quantity;
		this.vendorId = vendorId;
		this.shelfNo = shelfNo;
	}
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(long pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getVendorId() {
		return vendorId;
	}
	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}
	public long getShelfNo() {
		return shelfNo;
	}
	public void setShelfNo(long shelfNo) {
		this.shelfNo = shelfNo;
	}


	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", categoryId=" + categoryId + ", pricePerUnit="
				+ pricePerUnit + ", quantity=" + quantity + ", vendorId=" + vendorId + ", shelfNo=" + shelfNo + "]";
	}
	
}
