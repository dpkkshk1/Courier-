package com.courier.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courier.dao.CityDAO;
import com.courier.exception.CourierException;
import com.courier.model.City;

/**
 * Servlet implementation class AjaxState
 */
@WebServlet(description = "this one is for related city", urlPatterns = { "/AjaxState" })
public class AjaxState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxState() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		String sID =  request.getParameter("stateId");
		CityDAO obCityDAO = new CityDAO();
		List<City> list=null;
		try {
			list = obCityDAO.getCity(Integer.parseInt(sID));
			//System.out.println(list);
			PrintWriter out = response.getWriter();
			out.print("<div class='form-group'> <label for='sel1'>Select City:</label> <select class='form-control' name='city' id='city'>");
			for(City city :list){
				out.print(""
						+ "<option value="+city.getCityId()+">"+ city.getCityName()+"</option>"
						);
			}
			out.print("</select></div>");
			
		}  catch (CourierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
		
		
	}

}
