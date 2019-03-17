package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courier.exception.CourierException;
import com.courier.model.City;
import com.courier.util.ConnectionUtil;

public class CityDAO {

	public CityDAO() {
		// TODO Auto-generated constructor stub
	}
	public String getCityName(int cityId) throws CourierException{
		String cityName="";
		Connection connObj=null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
		    connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement("Select city_name from city where city_id=?");
			pstmt.setInt(1, cityId);
			result=pstmt.executeQuery();
			while(result.next()){
				
				 cityName = result.getString("city_name");
				
		
				
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
		return cityName; 
	}
	public  List<City> getCity(int stateId)throws CourierException{
		List<City> list = new ArrayList<>();
		Connection connObj=null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
		    connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement("Select * from city where fk_state_id=?");
			pstmt.setInt(1, stateId);
			result=pstmt.executeQuery();
			while(result.next()){
				int cityId = result.getInt(1);
				String cityName = result.getString(2);
				
				City obState = new City();
				obState.setCityId(cityId);
				obState.setCityName(cityName);
				
				list.add(obState);
				
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
		
		return list;
	}

}
