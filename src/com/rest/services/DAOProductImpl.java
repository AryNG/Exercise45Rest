package com.rest.services;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rest.model.Product;

@Path("/Product")
public class DAOProductImpl implements DAOProducts {
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	int rowsAffected = 0;
	String SQLst = "";
	Properties props =  new Properties();
	InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
	

	public Connection getConnection() {
		Connection conn;
		conn =  ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	 /*@GET //This is a test to verify if you can access to this implementation. 
	public String Test() {
		return "Test";
	}*/
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void SaveProduct(Product myProduct) {
		conn = getConnection();
		try {
			props.load(in);
			if (props!=null) {
				SQLst = props.getProperty("SQLSaveP"); 
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			stm = conn.prepareStatement(SQLst);
			stm.setString(1, myProduct.getNombreProduct());
			stm.setDouble(2, myProduct.getPrecioProduct());
			stm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stm.close();
				rs.close();
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean UpdateProduct(Product myProduct) {
		//Open Connection
		conn = getConnection();	
		//Use props.
		try {
		props.load(in);
		if(props!=null) {
		//Use SQL from our props doc. 
		SQLst = props.getProperty("SQLUpdateP"); 
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		try {
					
			//Statement connection /sentence
			stm = conn.prepareStatement(SQLst);
			stm.setString(1, myProduct.getNombreProduct());
			stm.setDouble(2, myProduct.getPrecioProduct());
			stm.setInt(3, myProduct.getIdProduct());
			//Execute statement
			rowsAffected = stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
		//Statements
				
	}
	@Override
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean DeleteProduct(int idProduct) {
		
		
		
		return false;
	}

	@Override
	public Product ReadProduct(int idProduct) {
		
		return null;
	}

	@Override
	public ArrayList<Product> ReadAllP() {
		
		return null;
	}

}
