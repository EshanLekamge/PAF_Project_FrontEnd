package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

/**
 * Servlet implementation class ResearchServlet
 */
@WebServlet("/ResearchServlet")
public class ResearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ResearchService researchObj = new ResearchService();
	
	private static Map getParasMap(HttpServletRequest request) 
	{
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			String queryString = scanner.hasNext() ? 
					scanner.useDelimiter("\\A").next() : ""; 
			scanner.close(); 
			String[] params = queryString.split("&"); 
			for (String param : params) 
			{
				String[] p = param.split("=");
				map.put(p[0], p[1]); 
			} 
			
		} 
		catch (Exception e) 
		{ 
	 
		} 
	return map; 
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String output = researchObj.insertResearchProject(
				Integer.parseInt(request.getParameter("researchID")), 
				request.getParameter("researchName"), 
				Integer.parseInt(request.getParameter("researcherId")), 
				request.getParameter("researcherName"),
				request.getParameter("researchCategory"),
				request.getParameter("researchDescription"),
				Float.parseFloat(request.getParameter("researchCost")),
				Integer.parseInt(request.getParameter("researchDuration")),
				request.getParameter("startDate"));
		
		response.getWriter().write(output); 
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		{ 
			Map paras = getParasMap(request);
			
			String output = researchObj.updateResearchProject(Integer.parseInt(paras.get("hidResearchIDSave").toString()), 
			paras.get("researchName").toString(), 
			Integer.parseInt(paras.get("researcherId").toString()), 
			paras.get("researcherName").toString(), 
			paras.get("researchCategory").toString(), 
			paras.get("researchDescription").toString(), 
			Float.parseFloat(paras.get("researchCost").toString()), 
			Integer.parseInt(paras.get("researchDuration").toString()), 
			paras.get("startDate").toString());
			
			response.getWriter().write(output); 
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request);
		
		String output = researchObj.deleteResearchProject(Integer.parseInt(paras.get("researchID").toString()));
		
		response.getWriter().write(output);
		
	}

}
