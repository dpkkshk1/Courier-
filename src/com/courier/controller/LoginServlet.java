package com.courier.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courier.dao.CustomerDAO;
import com.courier.dao.EmployeeDAO;
import com.courier.exception.CourierException;
import com.courier.model.Customer;
import com.courier.model.Employee;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String objCreation =  request.getParameter("empLogin");
		String page=null;
		if(objCreation.equals("employeeLogin")){
			Employee obEmployee = new Employee();
			obEmployee.setEmailId(email);
			obEmployee.setPassword(password);
			EmployeeDAO obEmployeeDAO = new EmployeeDAO();
			HttpSession session = request.getSession();
			
			try {
				int flag=obEmployeeDAO.employeeLogin(obEmployee);
				if(flag!=0){
					
					session.setAttribute("userid", flag);
					page="./employeehomepage.jsp";
				}else{
					page="./employeelogin.jsp";
				}
			} catch (CourierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(objCreation.equals("customerLogin")){
			Customer obCustomer = new Customer();
			obCustomer.setEmail(email);
			obCustomer.setPassword(password);
			CustomerDAO obCustomerDAO = new CustomerDAO();
			HttpSession session = request.getSession();
			
			try {
				int flag=obCustomerDAO.customerLogin(obCustomer);
				if(flag!=0){
					
					session.setAttribute("customerId", flag);
					page="./customerconsignments.jsp";
				}else{
					page="./customerlogin.jsp";
				}
			} catch (CourierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd= request.getRequestDispatcher(page);
		rd.forward(request, response);
	}

}
