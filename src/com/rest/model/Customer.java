package com.rest.model;

public class Customer {
	private int idcustomer;
	private String customerName;
	private String customerAddress;
	private int customerAge;
	private double customerHeight;
	private double customerWeight;
	private boolean isNearStore;
	
	public Customer () {}
	public Customer (int idcustomer, String customerName,  String customerAddress, int customerAge, double customerHeight, double customerWeight, boolean isNearStore ) {
		
	}
	public int getIdcustomer() {
		return idcustomer;
	}
	public void setIdcustomer(int idcustomer) {
		this.idcustomer = idcustomer;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public int getCustomerAge() {
		return customerAge;
	}
	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}
	public double getCustomerHeight() {
		return customerHeight;
	}
	public void setCustomerHeight(double customerHeight) {
		this.customerHeight = customerHeight;
	}
	public double getCustomerWeight() {
		return customerWeight;
	}
	public void setCustomerWeight(double customerWeight) {
		this.customerWeight = customerWeight;
	}
	public boolean getIsNearStore() {
		return isNearStore;
	}
	public void setIsNearStore(boolean isNearStore) {
		this.isNearStore = isNearStore;
	}
}
