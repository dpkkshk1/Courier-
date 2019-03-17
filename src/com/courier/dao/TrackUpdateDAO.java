package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.courier.exception.CourierException;
import com.courier.model.City;
import com.courier.model.Consignment;
import com.courier.model.TrackUpdate;
import com.courier.util.ConnectionUtil;

public class TrackUpdateDAO {

	public TrackUpdateDAO() {
		// TODO Auto-generated constructor stub
	}

	public List<TrackUpdate> fetchAllTrackUpdate() throws CourierException {
		List<TrackUpdate> alltrackUpdateList = new ArrayList<TrackUpdate>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Connection connObj = null;
		String query = "Select * from trackupdate  t join city c where t.fk_city_id =c.city_id ";
		try {
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			result = pstmt.executeQuery();
			while(result.next()){
				int consignmentId = result.getInt("fk_consignment_id");
				String cur_date = result.getString("cur_date");
				String cityName = result.getString("city_name");
				int city_id = result.getInt("fk_city_id");
				int status = result.getInt("status");
				Consignment obconsignment = new Consignment();
				obconsignment.setConsignmentId(consignmentId);
				City obcity = new City();
				obcity.setCityId(city_id);
				obcity.setCityName(cityName);
				TrackUpdate obTrackUpdate = new TrackUpdate(obconsignment, cur_date, obcity, status);
				alltrackUpdateList.add(obTrackUpdate);
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
		return alltrackUpdateList;

		
	}
	
	
	
public List<TrackUpdate> fetchTrackUpdate(TrackUpdate trackUpdate) throws CourierException {
		List<TrackUpdate> trackUpdateList = new ArrayList<TrackUpdate>();
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Connection connObj = null;
		String query = "Select * from trackupdate where fk_consignment_id=?";
		try {
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, trackUpdate.getConsignment().getConsignmentId());
			
			result = pstmt.executeQuery();
			while(result.next()){
				int consignmentId = result.getInt("fk_consignment_id");
				String cur_date = result.getString("cur_date");
				int city_id = result.getInt("fk_city_id");
				int status = result.getInt("status");
				Consignment obconsignment = new Consignment();
				obconsignment.setConsignmentId(consignmentId);
				City obcity = new City();
				obcity.setCityId(city_id);
				TrackUpdate obTrackUpdate = new TrackUpdate(obconsignment, cur_date, obcity, status);
				trackUpdateList.add(obTrackUpdate);
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
		return trackUpdateList;

		
	}
public List<TrackUpdate> fetchLiveUpdate(int consignmentId) throws CourierException {
	List<TrackUpdate> trackUpdateList = new ArrayList<TrackUpdate>();
	PreparedStatement pstmt = null;
	ResultSet result = null;
	Connection connObj = null;
	String query = "Select * from trackupdate where fk_consignment_id=? and status =1";
	try {
		connObj = ConnectionUtil.getConnection();
		pstmt = connObj.prepareStatement(query);
		pstmt.setInt(1, consignmentId);
		
		result = pstmt.executeQuery();
		while(result.next()){
			String cur_date = result.getString("cur_date");
			int city_id = result.getInt("fk_city_id");
			int status = result.getInt("status");
			Consignment obconsignment = new Consignment();
			obconsignment.setConsignmentId(consignmentId);
			City obcity = new City();
			obcity.setCityId(city_id);
			TrackUpdate obTrackUpdate = new TrackUpdate(obconsignment, cur_date, obcity, status);
			trackUpdateList.add(obTrackUpdate);
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
	return trackUpdateList;

	
}
	
	
public void insertDate(Connection connObj, TrackUpdate trackUpdate) throws CourierException {
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		String query = "update trackupdate set cur_date=? where fk_consignment_id=? and fk_city_id=?";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(2, trackUpdate.getConsignment().getConsignmentId());
			pstmt.setString(1, trackUpdate.getCurrentDate());
			pstmt.setInt(3, trackUpdate.getCity().getCityId());
			//pstmt.setInt(4, trackUpdate.getStatus());
			pstmt.executeUpdate();
			
			
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

		
	}
public void updateExchangepoints(Connection connObj, TrackUpdate trackUpdate) throws CourierException {
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "update trackupdate set status=1 where fk_consignment_id=? and fk_city_id=?";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, trackUpdate.getConsignment().getConsignmentId());
			//pstmt.setString(2, trackUpdate.getCurrentDate());
			pstmt.setInt(2, trackUpdate.getCity().getCityId());
			//pstmt.setInt(4, trackUpdate.getStatus());
			pstmt.executeUpdate();
			
			
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

		
	}

	public void insertTrackUpdate(Connection connObj, TrackUpdate trackUpdate) throws CourierException {
		
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "insert into trackupdate(fk_consignment_id,fk_city_id)values(?,?)";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, trackUpdate.getConsignment().getConsignmentId());
			//pstmt.setString(2, trackUpdate.getCurrentDate());
			pstmt.setInt(2, trackUpdate.getCity().getCityId());
			//pstmt.setInt(4, trackUpdate.getStatus());
			pstmt.executeUpdate();
			
			
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

		
	}

}
