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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.rest.model.Customer;

@Path("/Customer")

public class DAOCustomerImpl implements DAOcustomer {
	Connection conn = null;
	PreparedStatement stm = null;
	ResultSet rs =  null;
	String SQLsent = "";
	int rowsAffected = 0;
	
	
	private Connection getConnection() {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) //Specify that it'll receive data and on a Json type. 
	@Override
	public void SaveCustomer(Customer myCustomer) { //De-serialization
		String SQLsent = "insert into Customers(customerName, customerAddress, customerAge, customerHeight, customerWeight, isNearStore) values (?,?,?,?,?,?)";	
		conn = getConnection();
		try {
			stm = conn.prepareStatement(SQLsent);
			stm.setString(1, myCustomer.getCustomerName());
			stm.setString(2, myCustomer.getCustomerAddress());
			stm.setInt(3, myCustomer.getCustomerAge());
			stm.setDouble(4, myCustomer.getCustomerHeight());
			stm.setDouble(5, myCustomer.getCustomerWeight());
			stm.setBoolean(6, myCustomer.getIsNearStore());
			stm.executeUpdate();
			System.out.println("Task successfully completed.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	@DELETE
	public boolean DeletCustomer(@QueryParam("idCustomer")int idCostumer) {
		Properties props =  new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		conn = getConnection();
		boolean isDeleted =  false;
		
		try {
			props.load(in);
			if(props!=null){
				SQLsent = props.getProperty("SQLDelete");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			stm = conn.prepareStatement(SQLsent);
			rs = stm.executeQuery();
			isDeleted = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stm.close();
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isDeleted;
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean UpdateCustomer(Customer myCustomer) {
		//Open the connection
		conn = getConnection();
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		try {
			props.load(in);
			if(props!=null){
				SQLsent = props.getProperty("SQLUpdate");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			stm = conn.prepareStatement("update Customers set customerName =?, customerAddress = ?, customerAge = ?, customerHeight = ?, customerWeight = ?, isNearStore = ? where idCustomer = ?");
			stm.setString(1, myCustomer.getCustomerName());
			stm.setString(2, myCustomer.getCustomerAddress());
			stm.setInt(3, myCustomer.getCustomerAge());
			stm.setDouble(4, myCustomer.getCustomerHeight());
			stm.setDouble(5, myCustomer.getCustomerWeight());
			stm.setBoolean(6, myCustomer.getIsNearStore());
			stm.setInt(7, myCustomer.getIdcustomer());
			rowsAffected = stm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		return (rowsAffected != 0)?true:false;
	}

	@Override
	@GET
	@Path("/SingleCustomer") // Using a QueryParam or a Path we tell the program which "GET" to access, otherwise it'll generate a conflict to mime-types.
	public Customer readCustomer(@QueryParam("idCustomer")int idCustomer) {
		
		Properties props =  new Properties();
		conn = getConnection();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		Customer customerData = new Customer ();
		
		try {
			props.load(in);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		if(props!=null) {
			SQLsent =  props.getProperty("SQLReadC");
			}
			
		try {
			stm = conn.prepareStatement(SQLsent);
			rs = stm.executeQuery(SQLsent);
			rs.next();
			customerData.setIdcustomer(rs.getInt("idCustomer"));
			customerData.setCustomerName(rs.getString("customerName"));
			customerData.setCustomerAddress(rs.getString("customerAddress"));
			customerData.setCustomerAge(rs.getInt("customerAge"));
			customerData.setCustomerHeight(rs.getDouble("customerHeight"));
			customerData.setCustomerWeight(rs.getDouble("customerWeight"));
			customerData.setIsNearStore(rs.getBoolean("isNearStore"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return customerData;
	}

	@Override
	@GET
	public ArrayList<Customer> ReadAllCustomer() {
		ArrayList<Customer> myCustomers = new ArrayList <Customer>();
		
		conn  =  getConnection();
		Properties props =  new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		
			try {
				props.load(in);
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			if(props!=null) {
				SQLsent =  props.getProperty("SQLReadAll");
	
		try {
			stm = conn.prepareStatement(SQLsent);
			rs = stm.executeQuery();
			while(rs.next()) {
				Customer myCustomer = new Customer();
				myCustomer.setIdcustomer(rs.getInt("idCustomer"));
				myCustomer.setCustomerName(rs.getNString("customerName"));
				myCustomer.setCustomerAddress(rs.getNString("customerAddress"));
				myCustomer.setCustomerAge(rs.getInt("customerAge"));
				myCustomer.setCustomerHeight(rs.getDouble("customerHeight"));
				myCustomer.setCustomerWeight(rs.getDouble("customerWeight"));
				myCustomer.setIsNearStore(rs.getBoolean("isNearStore"));
				myCustomers.add(myCustomer);
			}
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
			return myCustomers;

	}
}
