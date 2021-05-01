package model;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.RequestValidator;
import com.ResearchService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/")
public class ResearchServiceModel {
	
	ResearchService researchObj = new ResearchService();
	RequestValidator requestValidator =  new RequestValidator();
	
	@POST
	@Path("/test")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public String test(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		return itemObject.toString();
	}
	
	
@POST
@Path("/insertresearchProject")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String addResearchProject(String researchData)
{
	JsonObject result = new JsonObject();
	result.addProperty("status", "error");
	int researchID = 0;
	String researchName = "";
	int researcherId = 0; 
	String researcherName = "";
	String researchCategory = "";
	String researchDescription = "";
	float researchCost = 0;
	int researchDuration = 0;
	String startDate = "";
		
	try {
		
		JsonObject research = new JsonParser().parse(researchData).getAsJsonObject();
		
		researcherId = research.get("user_id").getAsInt();

		if (research.has("researches")) {
			
			for (JsonElement singleItem : research.get("researches").getAsJsonArray()) 
			{
				JsonObject researchObject = singleItem.getAsJsonObject();
				
				researchID = researchObject.get("researchID").getAsInt();
				researchName = researchObject.get("researchName").getAsString();
				//researcherId = researchObject.get("researcherId").getAsInt();
				researcherName = researchObject.get("researcherName").getAsString();
				researchCategory = researchObject.get("researchCategory").getAsString();
				researchDescription = researchObject.get("researchDescription").getAsString();
				researchCost = researchObject.get("researchCost").getAsFloat();
				researchDuration = researchObject.get("researchDuration").getAsInt();
				startDate = researchObject.get("startDate").getAsString();
				
				researchObj.insertResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate);
				
			}
			
			result.addProperty("status", "done all");
				
		} 
		else if (research.has("researchID")) 
		{
				researchID = research.get("researchID").getAsInt();
				researchName = research.get("researchName").getAsString();
				//researcherId = research.get("researcherId").getAsInt();
				researcherName = research.get("researcherName").getAsString();
				researchCategory = research.get("researchCategory").getAsString();
				researchDescription = research.get("researchDescription").getAsString();
				researchCost = research.get("researchCost").getAsFloat();
				researchDuration = research.get("researchDuration").getAsInt();
				startDate = research.get("startDate").getAsString();
		
				//if (researchObj.insertResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate)) 
				//{
					//result.addProperty("status", "done");
				//}
				
		}
		
		}catch (Exception e) {
			e.printStackTrace();
			result.addProperty("status", "error");
		}

		return result.toString();
	}

@GET
@Path("/getResearchProjects")
@Produces(MediaType.TEXT_HTML)
public String getAllresearchProjects()
{
	return researchObj.getAllResearchProjects();
}

		
@GET
@Path("/getResearchProject")
@Produces(MediaType.TEXT_HTML)
public String getresearchProjects(@DefaultValue("0") @QueryParam("researchID") Integer research)
{
	return researchObj.getResearchProject(research);
}

@GET
@Path("/searchResearchProjects")
@Produces(MediaType.TEXT_HTML)
public String searchResearch(@DefaultValue("") @QueryParam("researchName") String research)
{
	return researchObj.searchResearchProjects(research);
}

@GET
@Path("/searchResearchProjectsCat")
@Produces(MediaType.TEXT_HTML)
public String searchResearchByCategory(@DefaultValue("") @QueryParam("researchCategory") String research)
{
	return researchObj.searchResearchProjectsByCategory(research);
}

@PUT
@Path("/updateresearchProject")
@Produces(MediaType.TEXT_HTML)
public String updateResearch(String researchData)
{
	JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();
	
	int researchID = researhObject.get("researchID").getAsInt();
	String researchName = researhObject.get("researchName").getAsString();
	int researcherId = researhObject.get("researcherId").getAsInt();
	String researcherName = researhObject.get("researcherName").getAsString();
	String researchCategory = researhObject.get("researchCategory").getAsString();
	String researchDescription = researhObject.get("researchDescription").getAsString();
	float researchCost = researhObject.get("researchCost").getAsFloat();
	int researchDuration  = researhObject.get("researchDuration").getAsInt();
	String startDate = researhObject.get("startDate").getAsString();

	return researchObj.updateResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate);

}


@DELETE
@Path("/deleteresearchProject")
@Produces(MediaType.TEXT_HTML)
public String deleteResearch(String researchData)
{
	JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();

	int researchID = researhObject.get("researchID").getAsInt();

	return researchObj.deleteResearchProject(researchID);
	
}
	

}
