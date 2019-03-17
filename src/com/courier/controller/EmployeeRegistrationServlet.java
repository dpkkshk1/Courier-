package com.courier.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courier.dao.AddressDAO;
import com.courier.dao.EmployeeDAO;
import com.courier.exception.CourierException;
import com.courier.model.Address;
import com.courier.model.City;
import com.courier.model.Employee;
import com.courier.model.State;
import com.courier.util.ConnectionUtil;

/**
 * Servlet implementation class EmployeeRegistrationServlet
 */
@WebServlet(description = "for employee registration", urlPatterns = { "/EmployeeRegistrationServlet" })
public class EmployeeRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeRegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String password = request.getParameter("empPassword");
		String employeeName = request.getParameter("empName");
		String dob = request.getParameter("dob");
		System.out.println(dob);
		String emailId = request.getParameter("email");
		String mobileNo = request.getParameter("empPhone");
		String stateId = request.getParameter("state");
		String cityId = request.getParameter("city");
		String pinCode = request.getParameter("pincode");
		String street = request.getParameter("street");
		State obState = new State();
		obState.setStateId(Integer.parseInt(stateId));
		Employee obEmployee = null;
		Connection connObj = null;
		try {
			connObj = ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);

			City obCity = new City();
			obCity.setCityId(Integer.parseInt(cityId));
			obCity.setState(obState);
			Address obAddress = new Address(street, pinCode, obCity);
			AddressDAO obAddressDAO = new AddressDAO();
			int addressId = obAddressDAO.insertAddress(connObj, obAddress);
			obAddress.setAddressId(addressId);
			obEmployee = new Employee(password, employeeName, dob, emailId, mobileNo, obAddress);
			EmployeeDAO obEmployeeDAO = new EmployeeDAO();
			int employeeId = obEmployeeDAO.insertEmployee(connObj, obEmployee);
			obEmployee.setEmployeeId(employeeId);
			connObj.commit();

		} catch (CourierException e) {
			try {
				connObj.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			try {
				connObj.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e);
		} finally {
			if (connObj != null) {
				try {
					connObj.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("./homepage.jsp");
		request.setAttribute("employee", obEmployee);
		rd.forward(request, response);

	}

}
