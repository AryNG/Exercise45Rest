package com.rest.services;

import java.sql.Connection;
import java.util.ArrayList;

import com.rest.model.Product;

public interface DAOProducts {
	public void SaveProduct (Product myProduct);
	public boolean UpdateProduct (Product myProduct);
	public boolean DeleteProduct (int idProduct);
	public Product ReadProduct (int idProduct);
	public ArrayList <Product> ReadAllP ();
	
}
