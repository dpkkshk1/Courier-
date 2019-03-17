package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.courier.exception.CourierException;
import com.courier.model.State;
import com.courier.util.ConnectionUtil;


public class StateDAO {

	public StateDAO() {
		// TODO Auto-generated constructor stub
	}

	public static List<State> getState()throws CourierException{
		List<State> list = new ArrayList<>();
		Connection connObj=null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
		    connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement("Select * from state");
			result=pstmt.executeQuery();
			while(result.next()){
				int stateId = result.getInt(1);
				String stateName = result.getString(2);
				
				State obState = new State();
				obState.setStateId(stateId);
				obState.setStateName(stateName);
				
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
			 if(connObj!=null){ System.out.println("DepartmentDAO"); try {
			 connObj.close(); } catch (SQLException e) { // TODO
				 }
			 }
			 

		}
		
		return list;
	}

}
