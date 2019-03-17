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

import com.courier.dao.TrackUpdateDAO;
import com.courier.exception.CourierException;
import com.courier.model.City;
import com.courier.model.Consignment;
import com.courier.model.TrackUpdate;
import com.courier.util.ConnectionUtil;

/**
 * Servlet implementation class UpdateTrackServlet
 */
@WebServlet(description = "this servlet is for updating tracking", urlPatterns = { "/UpdateTrackServlet" })
public class UpdateTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTrackServlet() {
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
		String currentDate = request.getParameter("dateUpdate");
		String status = request.getParameter("status");
		String consignmentId = request.getParameter("consignmentId");
		String cityId = request.getParameter("cityId");
		Consignment obConsignment = new Consignment();
		obConsignment.setConsignmentId(Integer.parseInt(consignmentId));
		City obCity = new City();
		obCity.setCityId(Integer.parseInt(cityId));
		TrackUpdate obTrackUpdate = new TrackUpdate();
		obTrackUpdate.setCity(obCity);
		obTrackUpdate.setConsignment(obConsignment);
		obTrackUpdate.setCurrentDate(currentDate);
		obTrackUpdate.setStatus(Integer.parseInt(status));
		TrackUpdateDAO obTrackUpdateDAO = new TrackUpdateDAO();
		Connection connObj = null;
		try {
			connObj = ConnectionUtil.getConnection();
			connObj.setAutoCommit(false);
			obTrackUpdateDAO.insertDate(connObj, obTrackUpdate);
			obTrackUpdateDAO.updateExchangepoints(connObj, obTrackUpdate);
			connObj.commit();

		} catch (CourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
