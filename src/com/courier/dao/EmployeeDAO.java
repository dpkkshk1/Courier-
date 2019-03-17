package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.courier.exception.CourierException;
import com.courier.model.Employee;
import com.courier.util.ConnectionUtil;


public class EmployeeDAO {

	public EmployeeDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public int employeeLogin(Employee obEmployee) throws CourierException {
		int flag = 0;
		 Connection conn = null;
		String querry = "select * from employee where email_id=? and password=? ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		conn = ConnectionUtil.getConnection();
		try {
			pst = conn.prepareStatement(querry);

			pst.setString(1, obEmployee.getEmailId());
			pst.setString(2, obEmployee.getPassword());
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
	
	public int insertEmployee(Connection connObj, Employee employee) throws CourierException {
		int generatedId = 0;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "insert into employee(password,emp_name,dob,email_id,mobile,fk_address_id)values(?,?,?,?,?,?)";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setString(1, employee.getPassword());
			pstmt.setString(2, employee.getEmployeeName());
			pstmt.setString(3, employee.getDob());
			pstmt.setString(4, employee.getEmailId());
			pstmt.setString(5, employee.getMobile());
			pstmt.setInt(6, employee.getAddress().getAddressId());
			pstmt.executeUpdate();
			result = pstmt.getGeneratedKeys();
			if (result.next()) {
				generatedId = result.getInt(1);
			}
		} catch (Exception e) {
			throw new CourierException("Exception in Employee_insertion dao " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (Exception e) {
				throw new CourierException("Exception while Closing in insertion in EmployeeDAO" + e.getMessage());
			}
		}

		return generatedId;
	}

}
