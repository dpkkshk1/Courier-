package com.courier.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courier.dao.CityDAO;
import com.courier.dao.ConsignmentDAO;
import com.courier.dao.TrackUpdateDAO;
import com.courier.exception.CourierException;
import com.courier.model.City;
import com.courier.model.TrackUpdate;

/**
 * Servlet implementation class ShowDataServlet
 */
@WebServlet(description = "showing track details for customer", urlPatterns = { "/ShowDataServlet" })
public class ShowDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int consignmentId = Integer.parseInt(request.getParameter("consignmentId"));
		List<TrackUpdate> list = new ArrayList<>();
		TrackUpdateDAO obTrackUpdateDAO = new TrackUpdateDAO();
		try {
			
			list = obTrackUpdateDAO.fetchLiveUpdate(consignmentId);
			for(TrackUpdate obTrackUpdate:list){
				int cityId=obTrackUpdate.getCity().getCityId();
				CityDAO obCityDAO = new CityDAO();
				String cityName=obCityDAO.getCityName(cityId);
				City obCity=obTrackUpdate.getCity();
				obCity.setCityName(cityName);
				
				
				
			}
			request.setAttribute("trackList", list);
			RequestDispatcher rd = request.getRequestDispatcher("./showtrackingdetails.jsp");
			rd.forward(request, response);
		} catch (CourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doPost(req, resp);
	}

}
