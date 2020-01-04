package com.rest.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.rest.model.Customer;

public interface DAOcustomer {
	
	public void SaveCustomer (Customer myCostumer);
	public boolean DeletCustomer (int idCostumer);
	public boolean UpdateCustomer (Customer myCostumer);
	public Customer readCustomer (int idCostumer);
	public ArrayList <Customer> ReadAllCustomer ();
}
