package com.courier.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courier.dao.TrackUpdateDAO;
import com.courier.exception.CourierException;
import com.courier.model.City;
import com.courier.model.Consignment;
import com.courier.model.TrackUpdate;
import com.courier.util.ConnectionUtil;

/**
 * Servlet implementation class UpdateExchangePointServlet
 */
@WebServlet(description = "this will update the track update", urlPatterns = { "/UpdateExchangePointServlet" })
public class UpdateExchangePointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateExchangePointServlet() {
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
		String currentDate = request.getParameter("currentDate");
		String status = request.getParameter("status");
		String cityId = request.getParameter("cityId");
		String consignmentId = request.getParameter("consignmentId");
		City obCity=new City();
		obCity.setCityId(Integer.parseInt(cityId));
		
		Consignment obConsignment=new Consignment();
		obConsignment.setConsignmentId(Integer.parseInt(consignmentId));
		
		TrackUpdate obTrackUpdate=new TrackUpdate();
		obTrackUpdate.setConsignment(obConsignment);
		obTrackUpdate.setCity(obCity);
		obTrackUpdate.setCurrentDate(currentDate);
		obTrackUpdate.setStatus(Integer.parseInt(status));
		
		Connection connObj = null;
		TrackUpdateDAO obTrackUpdateDAO = new TrackUpdateDAO();
		
		try {
			connObj = ConnectionUtil.getConnection();
			obTrackUpdateDAO.updateExchangepoints(connObj, obTrackUpdate);
			obTrackUpdateDAO.insertDate(connObj, obTrackUpdate);
			
		} catch (CourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
