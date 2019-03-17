package com.courier.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.courier.dao.CustomerDAO;
import com.courier.exception.CourierException;

/**
 * Servlet implementation class CustomerAjax
 */
@WebServlet(description = "customer id check", urlPatterns = { "/CustomerAjax" })
public class CustomerAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String emaiId= request.getParameter("email");
		CustomerDAO obCustomerDAO = new CustomerDAO();
		try {
			int flag=obCustomerDAO.checkCustomer(emaiId);
			PrintWriter out = response.getWriter();
			if(flag!=0){
				out.print("<h3>User is Available</h3>");
			}else{
				out.print("<h3>Go first register the user</h3>");
			}
		} catch (CourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emaiId= request.getParameter("email");
		CustomerDAO obCustomerDAO = new CustomerDAO();
		try {
			int flag=obCustomerDAO.checkCustomer(emaiId);
			PrintWriter out = response.getWriter();
			if(flag!=0){
				HttpSession session = request.getSession();
				session.setAttribute("custId", flag);
				out.print("<h3>User is Available</h3>");
			}else{
				out.print("<h3>Go first register the user</h3>");
			}
		} catch (CourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


