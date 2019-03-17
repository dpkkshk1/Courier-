package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.courier.exception.CourierException;
import com.courier.model.Consignment;
import com.courier.model.LiveTrack;
import com.courier.util.ConnectionUtil;

public class LiveTrackDAO {

	public LiveTrackDAO() {
		// TODO Auto-generated constructor stub
	}
	public List<Consignment> fetchAll(Consignment obConsignment)throws CourierException {
		List<Consignment> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection connObj = null;
		ResultSet result = null;
		String query = "Select * from livetrack where consignment_id = ? and fk_customer_id=?";
		try{
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, obConsignment.getConsignmentId());
			pstmt.setInt(2, obConsignment.getCustomer().getCustomerId());
			result = pstmt.executeQuery();
			while(result.next()){
				
			}
			
		}catch (Exception e) {
			throw new CourierException("Exception in TrackUpdateDAO_insertion dao " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (result != null) {
					result.close();
				}
				if(connObj != null){
					connObj.close();
				}
			} catch (Exception e) {
				throw new CourierException("Exception while Closing in insertion in TrackUpdateDAO" + e.getMessage());
			}
		}
		
		return list;
	}
	public int insertLiveTrack(Connection connObj, LiveTrack liveTrack) throws CourierException {
		int generatedId = 0;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "insert into livetrack(fk_customer_id,fk_consignment_id)values(?,?)";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, liveTrack.getConsignment().getConsignmentId());
			pstmt.executeUpdate();
			result = pstmt.getGeneratedKeys();
			if (result.next()) {
				generatedId = result.getInt(1);
			}
		} catch (Exception e) {
			throw new CourierException("Exception in TrackUpdateDAO_insertion dao " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (Exception e) {
				throw new CourierException("Exception while Closing in insertion in TrackUpdateDAO" + e.getMessage());
			}
		}

		return generatedId;
	}

}
