package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courier.exception.CourierException;
import com.courier.model.City;
import com.courier.model.Consignment;
import com.courier.util.ConnectionUtil;

public class ExchangePointsDAO {
	

	public ExchangePointsDAO() {
		// TODO Auto-generated constructor stub
	}
	public List<City> getExchangePoints(Consignment obConsignment)throws CourierException{
		List<City> cityList = new ArrayList<City>();
		Connection connObj = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			int cityFrom = obConsignment.getAddressFrom().getCity().getCityId();
			int cityTo = obConsignment.getAddressTo().getCity().getCityId();
			System.out.println("tc city "+cityFrom);
			System.out.println("tc city "+cityTo);
			if(cityFrom>cityTo){
				String query = "select * from exchangepoints where fk_city_from between ? and ?";
				connObj = ConnectionUtil.getConnection();
				pstmt = connObj.prepareStatement(query);
			pstmt.setInt(2, cityFrom);
			pstmt.setInt(1, cityTo);
			result = pstmt.executeQuery();
			}else{
				String query = "select * from exchangepoints where fk_city_from between ? and ? order by fk_city_from desc";
				connObj = ConnectionUtil.getConnection();
				pstmt = connObj.prepareStatement(query);
				pstmt.setInt(1, cityFrom);
				pstmt.setInt(2, cityTo);
				result = pstmt.executeQuery();
			}
			
			
			while(result.next()){
				City obCity = new City();
				obCity.setCityId(result.getInt(2));
				cityList.add(obCity);
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
		return cityList;
	}
	

}
