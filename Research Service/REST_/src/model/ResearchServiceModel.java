package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ResearchServiceModel {
	
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


public boolean insertResearchProject(int researchID, String researchName, int researcherId, String researcherName, String researchCategory, String researchDescription, float researchCost, int researchDuration, String startDate)
{
	String output = "";
	
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return false;
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
		
		
		
	}catch(Exception e)
	{
		System.out.print(e);
		return false;
	}
	return true;
	
}

public String getAllResearchProjects()
{
	JsonObject researchDetails = new JsonObject();
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return researchDetails.toString();
		}
		
		String readSql = "SELECT * FROM researchprojects_tb";
		PreparedStatement statement = con.prepareStatement(readSql);
		ResultSet results = statement.executeQuery();
		
		JsonArray researchArray = new JsonArray();
		
		while(results.next())
		{
			JsonObject researchObject = new JsonObject();
			
			researchObject.addProperty("researchID", results.getInt(1));
			researchObject.addProperty("researchName", results.getString(2));
			researchObject.addProperty("researcherId", results.getInt(3));
			researchObject.addProperty("researcherName", results.getString(4));
			researchObject.addProperty("researchCategory", results.getString(5));
			researchObject.addProperty("researchDescription", results.getString(6));
			researchObject.addProperty("researchCost", results.getFloat(7));
			researchObject.addProperty("researchDuration", results.getInt(8));
			researchObject.addProperty("startDate", results.getString(9));	
			
			researchArray.add(researchObject);
			
		}
		
		researchDetails.add("resarches", researchArray);
		
	}catch(Exception e)
	{
		System.out.print("Error While Reading from the Database");
		System.out.print(e);
		researchDetails.addProperty("status", "error");
		
	}
	
	return researchDetails.toString();
	
}


public String getResearchProject(int researchID)
{
	JsonObject researchDetails = new JsonObject();
	
	String output = "error";
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return researchDetails.toString();
		}
		
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchID = ? ";
		PreparedStatement statement = con.prepareStatement(readSql);
		
		statement.setInt(1, researchID);
		ResultSet results = statement.executeQuery();
		
		if(results.next())
		{
			researchDetails.addProperty("researchName", results.getString(2));
			researchDetails.addProperty("researcherId", results.getInt(3));
			researchDetails.addProperty("researcherName", results.getString(4));
			researchDetails.addProperty("researchCategory", results.getString(5));
			researchDetails.addProperty("researchDescription", results.getString(6));
			researchDetails.addProperty("researchCost", results.getFloat(7));
			researchDetails.addProperty("researchDuration", results.getInt(8));
			researchDetails.addProperty("startDate", results.getString(9));

		}
		
		con.close();
		
	}catch(Exception e)
	{
		System.out.print("Error While Reading from the Database");
		System.out.print(e);
		researchDetails.addProperty("status", "error");
		
	}
	
	return researchDetails.toString();
}


public String searchResearchProjects(String researchName)
{
	JsonObject researchDetails = new JsonObject();
	
	String output = "error";
	
	try {
		
		Connection con = connect();
		if(con == null)
		{
			return researchDetails.toString();
		}
		
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchName LIKE CONCAT( '%',?,'%')";
		PreparedStatement statement = con.prepareStatement(readSql);
		
		statement.setString(1, researchName);
		
		ResultSet results = statement.executeQuery();
		
		JsonArray researchArray = new JsonArray();
		
		
		while(results.next())
		{
			JsonObject researchObject = new JsonObject();
			
			researchObject.addProperty("researchName", results.getString(2));
			researchObject.addProperty("researcherId", results.getInt(3));
			researchObject.addProperty("researcherName", results.getString(4));
			researchObject.addProperty("researchCategory", results.getString(5));
			researchObject.addProperty("researchDescription", results.getString(6));
			researchObject.addProperty("researchCost", results.getFloat(7));
			researchObject.addProperty("researchDuration", results.getInt(8));
			researchObject.addProperty("startDate", results.getString(9));
			
			researchArray.add(researchObject);
		}
		
		researchDetails.add("resarches", researchArray);
		
		con.close();
		
	}catch(Exception e)
	{
		System.out.print("Error While Reading from the Database");
		System.out.print(e);
		researchDetails.addProperty("status", "error");
		
	}
	
	return researchDetails.toString();
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
		
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchCategory = ?";
		PreparedStatement statement = con.prepareStatement(readSql);
		
		statement.setString(1, researchCategory);
		
		ResultSet results = statement.executeQuery();
		
		JsonArray researchArray = new JsonArray();
		
		
		while(results.next())
		{
			JsonObject researchObject = new JsonObject();
			
			researchObject.addProperty("researchName", results.getString(2));
			researchObject.addProperty("researcherId", results.getInt(3));
			researchObject.addProperty("researcherName", results.getString(4));
			researchObject.addProperty("researchCategory", results.getString(5));
			researchObject.addProperty("researchDescription", results.getString(6));
			researchObject.addProperty("researchCost", results.getFloat(7));
			researchObject.addProperty("researchDuration", results.getInt(8));
			researchObject.addProperty("startDate", results.getString(9));
			researchArray.add(researchObject);
		}
		
		researchDetails.add("resarches", researchArray);
		
		con.close();
		
	}catch(Exception e)
	{
		System.out.print("Error While Reading from the Database");
		System.out.print(e);
		researchDetails.addProperty("status", "error");
		
	}
	
	return researchDetails.toString();
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
		
		String insertSql = "UPDATE researchprojects_tb SET researchName = ?, researcherId = ?, researcherName = ?, researchCategory = ?, researchDescription = ?, researchCost = ?, researchDuration = ?, startDate = ? WHERE researchID = ?";
		
		PreparedStatement statement = con.prepareStatement(insertSql);
		
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
		
		output = "Updated Successfully";
		
	}catch(Exception e)
	{
		output = "Error while Updateding.";
		System.out.print(e);
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
			String deleteSql = "DELETE FROM researchprojects_tb WHERE researchID = ? ";
			
			PreparedStatement statement = con.prepareStatement(deleteSql);
			statement.setInt(1, researchID);
			statement.executeUpdate();
			con.close();
			
			output = "Deleted Successfully";
			
	}
	catch(Exception e)
	{
		output = "Error while Deleting";
		System.out.println(e);
		
	}
	
	return output;
	
}


}
