package com.courier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.courier.exception.CourierException;
import com.courier.model.Address;
import com.courier.model.City;
import com.courier.model.Consignment;
import com.courier.model.Customer;
import com.courier.model.Employee;
import com.courier.util.ConnectionUtil;

public class ConsignmentDAO {

	public ConsignmentDAO() {
		// TODO Auto-generated constructor stub
	}
	public List<Consignment> listConsignment(int obConsignment)throws CourierException {
		
		List<Consignment> list= new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection connObj = null;
		ResultSet result = null;
		
		String query = "select * from consignment where fk_customer_id=? ";
		try{
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, obConsignment);
			result = pstmt.executeQuery();
			while(result.next()){
				int consignmentId = result.getInt("consignment_id");
				String curDate = result.getString("accpted_date");
				double packageWeight = result.getDouble("package_weight");
				double cost = result.getDouble("cost");
				int addressFrom = result.getInt("fk_address_from");
				int addressTo = result.getInt("fk_address_to");
				int employeeId = result.getInt("fk_employee_id");
				int customerId = result.getInt("fk_customer_id");
				AddressDAO obAddressDAO = new AddressDAO();
				int cityIdFrom = obAddressDAO.getCityId(addressFrom);
				System.out.println("cityIdFrom"+cityIdFrom);
				int cityIdTo = obAddressDAO.getCityId(addressTo);
				System.out.println("cityIdTo");
				CityDAO obCityDAO = new CityDAO();
				System.out.println("obCityDAO");
				String cityFromName=obCityDAO.getCityName(cityIdFrom);
				System.out.println("cityFromName");
				String cityToName=obCityDAO.getCityName(cityIdTo);
				System.out.println("cityToName");
				
				Customer cust = new Customer();
				System.out.println("cust");
				cust.setCustomerId(customerId);
				System.out.println("setcustId");
				Address adTo = new Address();
				System.out.println("adTo");
				adTo.setAddressId(addressTo);
				System.out.println("setaddressTo");
				City obCityTo = new City();
				System.out.println("obCityTo");
				obCityTo.setCityId(cityIdTo);
				System.out.println("setCityIdTO");
				obCityTo.setCityName(cityToName);
				System.out.println("setCityNameTO");
				adTo.setCity(obCityTo);
				System.out.println("setCity TO id");
				Address adFrom = new Address();
				System.out.println("adFrom");
				adFrom.setAddressId(addressFrom);
				System.out.println("setaddressIDFom");
				City obCityFrom = new City();
				System.out.println("obCityFrom");
				obCityFrom.setCityId(cityIdFrom);
				System.out.println("setCITYFROMID");
				obCityFrom.setCityName(cityFromName);
				System.out.println("setCITYFROMNAME");
				adFrom.setCity(obCityFrom);
				System.out.println("SETCITY ID FROM");
				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				Consignment consignment = new Consignment(consignmentId, curDate, packageWeight, cost, adFrom, adTo, employee, cust);
				list.add(consignment);
				
			}
			
		}catch (Exception e) {
			throw new CourierException("Exception in ListConsignment " + e.getMessage());
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
	public Consignment fetchAllConsignment(Consignment obConsignment)throws CourierException {
		
		PreparedStatement pstmt = null;
		Connection connObj = null;
		ResultSet result = null;
		Consignment consignment=null;
		String query = "Select * from consignment where consignment_id = ? ";
		try{
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, obConsignment.getConsignmentId());
			pstmt.setInt(2, obConsignment.getCustomer().getCustomerId());
			result = pstmt.executeQuery();
			while(result.next()){
				int consignmentId = result.getInt("consignment_id");
				String curDate = result.getString("accpted_date");
				double packageWeight = result.getDouble(" package_weight");
				double cost = result.getDouble("cost");
				int addressFrom = result.getInt("fk_address_from");
				int addressTo = result.getInt("fk_address_to");
				int employeeId = result.getInt("fk_employee_id");
				int customerId = result.getInt("fk_customer_id");
				Customer cust = new Customer();
				cust.setCustomerId(customerId);
				Address adTo = new Address();
				adTo.setAddressId(addressTo);
				Address adFrom = new Address();
				adFrom.setAddressId(addressFrom);
				Employee employee = new Employee();
				employee.setEmployeeId(employeeId);
				consignment = new Consignment(consignmentId, curDate, packageWeight, cost, adFrom, adTo, employee, cust);
				
				
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
		
		return consignment;
	}
	public int insertConsignment(Connection connObj, Consignment consignment) throws CourierException {
		int generatedId = 0;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String query = "insert into consignment(accpted_date,package_weight,cost,fk_address_from,fk_address_to,fk_employee_id,fk_customer_id)values(?,?,?,?,?,?,?)";
		try {
			pstmt = connObj.prepareStatement(query);
			pstmt.setString(1, consignment.getAccptedDate());
			pstmt.setDouble(2, consignment.getPackageWeight());
			pstmt.setDouble(3, consignment.getCost());
			pstmt.setInt(4, consignment.getAddressFrom().getAddressId());
			pstmt.setInt(5, consignment.getAddressTo().getAddressId());
			pstmt.setInt(6, consignment.getEmployee().getEmployeeId());
			pstmt.setInt(7, consignment.getCustomer().getCustomerId());
			pstmt.executeUpdate();
			result = pstmt.getGeneratedKeys();
			if (result.next()) {
				generatedId = result.getInt(1);
			}
		} catch (Exception e) {
			throw new CourierException("Exception in Consignment_insertion dao " + e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (result != null) {
					result.close();
				}
			} catch (Exception e) {
				throw new CourierException("Exception while Closing in insertion in ConsignmentDAO" + e.getMessage());
			}
		}

		return generatedId;
	}

}
