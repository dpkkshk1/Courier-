package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.courier.exception.CourierException;
import com.courier.model.Customer;
import com.courier.util.ConnectionUtil;

public class CustomerDAO {

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public int customerLogin(Customer obCustomer) throws CourierException {
		int flag = 0;
		 Connection conn = null;
		String querry = "select * from customer where email=? and password=? ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		conn = ConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(querry);

			pst.setString(1, obCustomer.getEmail());
			pst.setString(2, obCustomer.getPassword());
			rs = pst.executeQuery();
			if (rs.next()) {
				flag = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CourierException("You have a Exception in Login Dao"+e.getMessage());
		}finally{
			if(conn!=null){
				try{
					conn.close();
				}catch(Exception e){
					throw new CourierException("You have a Exception in closing Login Dao connection "+e.getMessage());
 
				}
			}
		}

		return flag;
	}
	public int checkCustomer(String email)throws CourierException{
		int flag =0;
				Connection connObj = null;
				PreparedStatement pstmt = null;
				ResultSet result = null;
				String query = "select * from customer where email =?";
				try {
					connObj = ConnectionUtil.getConnection();
					pstmt = connObj.prepareStatement(query);
					pstmt.setString(1, email);
					result = pstmt.executeQuery();
					if(result.next()){
						flag = result.getInt(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					throw new CourierException("Error in Cutomer check in CustomerDAO : " + e.getMessage());
				} finally {
					if (result != null) {
						try {
							result.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							throw new CourierException("Error in Cutomer check while closing result in CustomerDAO : : " + e.getMessage());
						}
					}
					if (pstmt != null) {
						try {
							pstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					  if(connObj!=null){ try { connObj.close(); } catch (SQLException
					  e) { // TODO Auto-generated catch block e.printStackTrace(); } }
						  throw new CourierException("Error in Cutomer check while closing connection in CustomerDAO : :" + e.getMessage()); 
					  }
					  }

				}
		
		return flag;
	}
	public int insertCustomer(Connection connObj, Customer customer) throws CourierException {
		int generatedId = 0;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "insert into customer(password,customer_name,mobile,email,fk_address_id)values(?,?,?,?,?)";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setString(1, customer.getPassword());
			pstmt.setString(2, customer.getCustomerName());
			pstmt.setString(3, customer.getMobile());
			pstmt.setString(4, customer.getEmail());
			pstmt.setInt(5, customer.getAddress().getAddressId());
			pstmt.executeUpdate();
			result = pstmt.getGeneratedKeys();
			if (result.next()) {
				generatedId = result.getInt(1);
			}
		} catch (Exception e) {
			throw new CourierException("Exception in Customer_insertion dao " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (Exception e) {
				throw new CourierException("Exception while Closing in insertion in  CustomerDAO" + e.getMessage());
			}
		}

		return generatedId;
	}

}
