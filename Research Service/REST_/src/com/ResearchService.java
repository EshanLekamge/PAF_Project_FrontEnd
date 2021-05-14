package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ResearchService {
	
	private Connection connect(){
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/research_db", "paf_user", "^paf_user_pw_000");
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return con;
		
	}

public String getAllResearchProjects()
{
	String output = ""; 
	 try
	 {
		 Connection con = connect(); 
		 if (con == null) 
		 {
			 return "Error while connecting to the database for reading.";
		 }
		 // Prepare the html table to be displayed
		 
		 output = "<table border='1'><tr><th>Research ID</th><th>Research Name</th><th>Researcher ID</th>"
		 + "<th>Researcher Name</th><th>Research Category</th><th>Research Description</th>"
		 + "<th>Research Cost</th><th>Research Duration</th><th>Start Date</th><th>Update</th><th>Remove</th></tr>";
		 
		 String readQuery = "SELECT * FROM researchprojects_tb";
		 Statement statement = con.createStatement();
		 ResultSet rs = statement.executeQuery(readQuery);
		 
		 //iterate through the rows in the result set
		 while (rs.next())
		 {
			 String researchID = Integer.toString(rs.getInt("researchID"));
			 String researchName = rs.getString("researchName");
			 String researcherId = Integer.toString(rs.getInt("researcherId"));
			 String researcherName = rs.getString("researcherName");
			 String researchCategory = rs.getString("researchCategory");
			 String researchDescription = rs.getString("researchDescription");
			 String researchCost = rs.getString("researchCost");
			 String researchDuration = rs.getString("researchDuration");
			 String startDate = rs.getString("startDate");
			 
			 
			 // Add into the html table
			 output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + researchID+ "'>" + researchID + "</td>"; 
			 output += "<td>" + researchName + "</td>";
			 output += "<td>" + researcherId + "</td>";
			 output += "<td>" + researcherName + "</td>";
			 output += "<td>" + researchCategory + "</td>";
			 output += "<td>" + researchDescription + "</td>";
			 output += "<td>" + researchCost + "</td>";
			 output += "<td>" + researchDuration + "</td>";
			 output += "<td>" + startDate + "</td>";
			 
			 // buttons
			 //output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary </td>"
			 //+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger'data-researchid='"+ researchID + "'>" + "</td></tr>";
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary' data-researchid='" + researchID + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-researchid='" + researchID + "'></td></tr>";
		}
		 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage()); 
	 } 
	 
	return output; 
}

public String insertResearchProject(int researchID, String researchName, int researcherId, String researcherName, String researchCategory, String researchDescription, float researchCost, int researchDuration, String startDate)
{
	String output = "";
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return "error";
		}
		
		String insertSql = "INSERT INTO researchprojects_tb VALUES (?,?,?,?,?,?,?,?,?)";
		PreparedStatement statement = con.prepareStatement(insertSql);
		
		statement.setInt(1, researchID);
		statement.setString(2, researchName);
		statement.setInt(3, researcherId);
		statement.setString(4, researcherName);
		statement.setString(5, researchCategory);
		statement.setString(6, researchDescription);
		statement.setFloat(7, researchCost);
		statement.setInt(8, researchDuration);
		statement.setString(9, startDate);
		
		statement.execute();
		con.close();
		
		String newResearches = getAllResearchProjects();
		output = "{\"status\":\"success\", \"data\": \""+newResearches+"\"}";
		
	}
	catch(Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while inserting Research Details.\"}"; 
		System.err.println(e.getMessage()); 
		
	}
	
	return output;
	
}



public String getResearchProject(int researchID)
{
	String output = "";
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return "Error while connecting to the database for reading."; 
		}
		
		// Prepare the html table to be displayed
		
		output = "<table border=\"1\"><tr><th>Research ID</th><th>Research Name</th><th>Researcher Id</th><th>Researcher Name</th><th>Research Category</th><th>Research Description</th><th>Research Cost</th><th>Research Duration</th><th>Start Date</th><th>Update</th><th>Delete</th></tr>"; 
		
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchID = ? ";
		PreparedStatement statement = con.prepareStatement(readSql);
		
		statement.setInt(1, researchID);
		ResultSet results = statement.executeQuery();
		
		if(results.next())
		{
			researchID = results.getInt("researchID"); 
			String researchName = results.getString("researchName"); 
			String researcherId = Integer.toString(results.getInt("researcherId")); 
			String researcherName = results.getString("researcherName"); 
			String researchCategory = results.getString("researchCategory");
			String researchDescription = results.getString("researchDescription");
			String researchCost = Float.toString(results.getFloat("researchCost"));
			String researchDuration = results.getString("researchDuration");
			String startDate = results.getString("startDate");

			// Add into the html table
			
			 output += "<tr>" + researchID  + "</td>"; 
			 output += "<td>" + researchName + "</td>"; 
			 output += "<td>" + researcherId + "</td>"; 
			 output += "<td>" + researcherName + "</td>"; 
			 output += "<td>" + researchCategory + "</td>";
			 output += "<td>" + researchDescription + "</td>"; 
			 output += "<td>" + researchCost + "</td>"; 
			 output += "<td>" + researchDuration + "</td>"; 
			 output += "<td>" + startDate + "</td>"; 
			
			// buttons
			output += "<td><input name='btnUpdate' "
					+ "type='button' value='Update' "
					+ " class=' btnUpdate btn btn-secondary' data-researchid = '" + researchID +"'></td>"
					+ "<td><form method='post' action='index.jsp'>"
					+ "<input name='btnRemove' type='button' "
					+ "value='Remove' class='btn btn-danger' data-researchid = '" + researchID +"'></td></tr>"; 
		}
		
		con.close();
		// Complete the html table
		output += "</table>"; 
		
	}catch(Exception e)
	{
		output = "Error while reading the items."; 
		System.err.println(e.getMessage()); 
		
	}
	
	return output;
}


public String searchResearchProjects(String researchName)
{
	String SearchOutput = "";
	String output = "";
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return "Error while connecting to the database for reading."; 
		}
		
		// Prepare the html table to be displayed
		
		output = "<table border=\"1\"><tr><th>Research ID</th><th>Research Name</th><th>Researcher Id</th><th>Researcher Name</th><th>Research Category</th><th>Research Description</th><th>Research Cost</th><th>Research Duration</th><th>Start Date</th><th>Update</th><th>Delete</th></tr>"; 
		
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchName LIKE CONCAT( '%',?,'%')";
		PreparedStatement statement = con.prepareStatement(readSql);
		
		statement.setString(1, researchName);
		
		ResultSet results = statement.executeQuery();
		
		JsonArray researchArray = new JsonArray();
		
		
		while(results.next())
		{
			String researchID = Integer.toString(results.getInt("researchID")); 
			researchName = results.getString("researchName"); 
			String researcherId = Integer.toString(results.getInt("researcherId")); 
			String researcherName = results.getString("researcherName"); 
			String researchCategory = results.getString("researchCategory");
			String researchDescription = results.getString("researchDescription");
			String researchCost = Float.toString(results.getFloat("researchCost"));
			String researchDuration = results.getString("researchDuration");
			String startDate = results.getString("startDate");

			// Add into the html table
			
			 output += "<tr>" + researchID  + "</td>"; 
			 output += "<td>" + researchName + "</td>"; 
			 output += "<td>" + researcherId + "</td>"; 
			 output += "<td>" + researcherName + "</td>"; 
			 output += "<td>" + researchCategory + "</td>";
			 output += "<td>" + researchDescription + "</td>"; 
			 output += "<td>" + researchCost + "</td>"; 
			 output += "<td>" + researchDuration + "</td>"; 
			 output += "<td>" + startDate + "</td>"; 
			
			// buttons
			 output += "<td><input name='btnUpdate' "
						+ "type='button' value='Update' "
						+ " class=' btnUpdate btn btn-secondary' data-researchid = '" + researchID +"'></td>"
						+ "<td><form method='post' action='index.jsp'>"
						+ "<input name='btnRemove' type='button' "
						+ "value='Remove' class='btn btn-danger' data-researchid = '" + researchID +"'></td></tr>"; 
		}
		
		con.close();
		output += "</table>"; 
		
		SearchOutput = "{\"status\":\"found\", \"data\": \""+output+"\"}";
		
	}catch(Exception e)
	{
		SearchOutput = "{\"status\":\"error\", \"data\": \"Error while Searching Research Details.\"}"; 
		System.err.println(e.getMessage());
		
	}
	
	return SearchOutput;
}


public String searchResearchProjectsByCategory(String researchCategory)
{
	JsonObject researchDetails = new JsonObject();
	
	String output = "error";
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return researchDetails.toString();
		}
		
		// Prepare the html table to be displayed
		
		output = "<table border=\"1\"><tr><th>Research ID</th><th>Research Name</th><th>Researcher Id</th><th>Researcher Name</th><th>Research Category</th><th>Research Description</th><th>Research Cost</th><th>Research Duration</th><th>Start Date</th><th>Update</th><th>Delete</th></tr>"; 
		
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchCategory = ?";
		PreparedStatement statement = con.prepareStatement(readSql);
		
		statement.setString(1, researchCategory);
		
		ResultSet results = statement.executeQuery();
		
		JsonArray researchArray = new JsonArray();
		
		
		while(results.next())
		{
			String researchID = Integer.toString(results.getInt("researchID")); 
			String researchName = results.getString("researchName"); 
			String researcherId = Integer.toString(results.getInt("researcherId")); 
			String researcherName = results.getString("researcherName"); 
			researchCategory = results.getString("researchCategory");
			String researchDescription = results.getString("researchDescription");
			String researchCost = Float.toString(results.getFloat("researchCost"));
			String researchDuration = results.getString("researchDuration");
			String startDate = results.getString("startDate");

			// Add into the html table
			
			 output += "<tr>" + researchID  + "</td>"; 
			 output += "<td>" + researchName + "</td>"; 
			 output += "<td>" + researcherId + "</td>"; 
			 output += "<td>" + researcherName + "</td>"; 
			 output += "<td>" + researchCategory + "</td>";
			 output += "<td>" + researchDescription + "</td>"; 
			 output += "<td>" + researchCost + "</td>"; 
			 output += "<td>" + researchDuration + "</td>"; 
			 output += "<td>" + startDate + "</td>"; 
			
			// buttons
			 output += "<td><input name='btnUpdate' "
						+ "type='button' value='Update' "
						+ " class=' btnUpdate btn btn-secondary' data-researchid = '" + researchID +"'></td>"
						+ "<td><form method='post' action='index.jsp'>"
						+ "<input name='btnRemove' type='button' "
						+ "value='Remove' class='btn btn-danger' data-researchid = '" + researchID +"'></td></tr>"; 

		}
		
		con.close();
		
	}catch(Exception e)
	{
		output = "Error while reading the items."; 
		System.err.println(e.getMessage()); 
		
	}
	
	return output;
}


public String updateResearchProject(int researchID, String researchName, int researcherId, String researcherName, String researchCategory, String researchDescription, float researchCost, int researchDuration, String startDate)
{
	String output = "";
	
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return "Error while connecting to the database for updating";
		}
		
		String updateSql = "UPDATE researchprojects_tb SET researchName = ?, researcherId = ?, researcherName = ?, researchCategory = ?, researchDescription = ?, researchCost = ?, researchDuration = ?, startDate = ? WHERE researchID = ?";
		
		PreparedStatement statement = con.prepareStatement(updateSql);
		
		//binding values
		statement.setString(1, researchName);
		statement.setInt(2, researcherId);
		statement.setString(3, researcherName);
		statement.setString(4, researchCategory);
		statement.setString(5, researchDescription);
		statement.setFloat(6, researchCost);
		statement.setInt(7, researchDuration);
		statement.setString(8, startDate);
		statement.setInt(9, researchID);
		
		statement.execute();
		con.close();
		
		String newResearches = getAllResearchProjects();
		output = "{\"status\":\"success\", \"data\": \""+newResearches+"\"}";
		
	}catch(Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while updating Research Details.\"}"; 
		System.err.println(e.getMessage()); 
	}
	return output;
	
}

public String deleteResearchProject(int researchID)
{
	String output = "";
	
	try 
	{
		Connection con = connect();
		
		if(con == null)
		{
			return "Error while connecting to the database for deleting";
		}
		
		//prepared statement
		String deleteSql = "DELETE FROM researchprojects_tb WHERE researchID = ? ";
			
		PreparedStatement statement = con.prepareStatement(deleteSql);
		
		//button values
		statement.setInt(1, researchID);
		
		//execute statement
		statement.executeUpdate();
		con.close();
			
		String newResearches = getAllResearchProjects();
		output = "{\"status\":\"success\", \"data\": \""+newResearches+"\"}";
		
		
	}
	catch(Exception e)
	{
		output = "{\"status\":\"error\", \"data\": \"Error while deleting Research Details.\"}"; 
		System.err.println(e.getMessage()); 
	}
	
	return output;
	
}


}
