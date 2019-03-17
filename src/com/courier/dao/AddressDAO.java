package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.courier.exception.CourierException;
import com.courier.model.Address;
import com.courier.util.ConnectionUtil;

public class AddressDAO {

	public AddressDAO() {
		// TODO Auto-generated constructor stub
	}
	public int getCityId(int addressId)throws CourierException{
		int cityId=0;
		Connection connObj=null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
		    connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement("Select fk_city_id from address where address_id=?");
			pstmt.setInt(1, addressId);
			result=pstmt.executeQuery();
			while(result.next()){
				
				 cityId = result.getInt("fk_city_id");
				
		
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CourierException("Error in StateDao : " + e.getMessage());
		} finally {
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
			 if(connObj!=null){ try {
			 connObj.close(); } catch (SQLException e) { // TODO
				 }
			 }
			

		}
		return cityId; 
	}
	public int insertAddress(Connection connObj, Address address) throws CourierException {
		int generatedId = 0;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "insert into address(street,pincode,fk_city_id)values(?,?,?)";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setString(1, address.getStreet());
			pstmt.setString(2, address.getPincode());
			pstmt.setInt(3, address.getCity().getCityId());
			pstmt.executeUpdate();
			result = pstmt.getGeneratedKeys();
			if (result.next()) {
				generatedId = result.getInt(1);
			}
		} catch (Exception e) {
			throw new CourierException("Exception in Address_insertion dao " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (Exception e) {
				throw new CourierException("Exception while Closing in insertion in AddressDAO" + e.getMessage());
			}
		}

		return generatedId;
	}

}
