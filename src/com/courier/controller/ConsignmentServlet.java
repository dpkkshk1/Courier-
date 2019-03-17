package com.courier.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courier.dao.AddressDAO;
import com.courier.dao.ConsignmentDAO;
import com.courier.dao.CustomerDAO;
import com.courier.dao.ExchangePointsDAO;
import com.courier.dao.TrackUpdateDAO;
import com.courier.exception.CourierException;
import com.courier.model.Address;
import com.courier.model.City;
import com.courier.model.Consignment;
import com.courier.model.Customer;
import com.courier.model.Employee;
import com.courier.model.State;
import com.courier.model.TrackUpdate;
import com.courier.util.ConnectionUtil;

/**
 * Servlet implementation class ConsignmentServlet
 */
@WebServlet(description = "register Consignment", urlPatterns = { "/ConsignmentServlet" })
public class ConsignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsignmentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String custEmail = request.getParameter("customerId");
		System.out.println(custEmail);
		double packWeight = Double.parseDouble(request.getParameter("packweight"));
		System.out.println(packWeight);
		String acceptedDate = request.getParameter("acceptedDate");
		System.out.println(acceptedDate);
		String street[] = request.getParameterValues("street");
		System.out.println(street[0]);
		String state[] = request.getParameterValues("state");
		System.out.println(state[0]);
		String city[] = request.getParameterValues("city");
		String pincode[] = request.getParameterValues("pincode");
		System.out.println(pincode[0]);
	    double cost = Double.parseDouble(request.getParameter("cost"));
		
		State obStateTo = new State();
		State obStateFrom = new State();
		obStateFrom.setStateId(Integer.parseInt(state[0]));
		
		obStateTo.setStateId(Integer.parseInt(state[1]));
		City obCityTo = new City(Integer.parseInt(city[1]),obStateTo);
		City obCityFrom = new City(Integer.parseInt(city[0]),obStateFrom);
		
		Address obAddressTo = new Address(street[1],pincode[1],obCityTo);
		Address obAddressFrom = new Address(street[0],pincode[0],obCityFrom);
		HttpSession session = request.getSession();
		int empId = (int)session.getAttribute("userid");
		Employee obEmployee = new Employee();
		obEmployee.setEmployeeId(empId);
		Customer obCustomer = new Customer();
		CustomerDAO obCustomerDAO = new CustomerDAO();
		
		int custId=0;
		try {
			custId = obCustomerDAO.checkCustomer(custEmail);
		} catch (CourierException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		obCustomer.setCustomerId(custId);
		Connection connObj = null;
		Consignment obConsignment = null;
		try {
			connObj = ConnectionUtil.getConnection();
		
			AddressDAO obAddressDAOTo = new AddressDAO();
			AddressDAO obAddressDAOFrom = new AddressDAO();
			int addressIdFrom = obAddressDAOFrom.insertAddress(connObj, obAddressFrom);
			int addressIdTo = obAddressDAOTo.insertAddress(connObj, obAddressTo);
			obAddressTo.setAddressId(addressIdTo);
			obAddressFrom.setAddressId(addressIdFrom);
			
			
			
			obConsignment = new Consignment(acceptedDate, packWeight, cost, obAddressFrom, obAddressTo, obEmployee, obCustomer);
			ConsignmentDAO obConsignmentDAO= new ConsignmentDAO();
			int consId=obConsignmentDAO.insertConsignment(connObj, obConsignment);
			System.out.println("consid"+consId);
			obConsignment.setConsignmentId(consId);
			ExchangePointsDAO obExchangePointsDAO = new ExchangePointsDAO();
			List<City> list=  obExchangePointsDAO.getExchangePoints(obConsignment);
			System.out.println(list);
			for (City obCity : list) {
				TrackUpdate obTrackUpdate = new TrackUpdate();
				obTrackUpdate.setConsignment(obConsignment);
				obTrackUpdate.setCity(obCity);
				TrackUpdateDAO obTrackUpdateDAO = new TrackUpdateDAO();
				
				obTrackUpdateDAO.insertTrackUpdate(connObj, obTrackUpdate);
			}
			System.out.println(obConsignment);
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
	 
		RequestDispatcher rd = request.getRequestDispatcher("./employeehomepage.jsp");
		rd.forward(request, response);
	
	}

}
