package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.ResearchServiceModel;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/")
public class ResearchService {

	ResearchServiceModel researchServiceModel = new ResearchServiceModel();
	RequestValidator requestValidator = new RequestValidator();

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
	public String addResearchProject(String researchData) {
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

			// request validation
			if (!requestValidator.validate(research.get("key").getAsString())) {

				return result.toString();
			}

			researcherId = research.get("user_id").getAsInt();

			if (research.has("researches")) {

				for (JsonElement singleItem : research.get("researches").getAsJsonArray()) {
					JsonObject researchObject = singleItem.getAsJsonObject();

					researchID = researchObject.get("researchID").getAsInt();
					researchName = researchObject.get("researchName").getAsString();
					// researcherId = researchObject.get("researcherId").getAsInt();
					researcherName = researchObject.get("researcherName").getAsString();
					researchCategory = researchObject.get("researchCategory").getAsString();
					researchDescription = researchObject.get("researchDescription").getAsString();
					researchCost = researchObject.get("researchCost").getAsFloat();
					researchDuration = researchObject.get("researchDuration").getAsInt();
					startDate = researchObject.get("startDate").getAsString();

					researchServiceModel.insertResearchProject(researchID, researchName, researcherId, researcherName,
							researchCategory, researchDescription, researchCost, researchDuration, startDate);

				}

				result.addProperty("status", "done all");

			} else if (research.has("researchID")) {
				researchID = research.get("researchID").getAsInt();
				researchName = research.get("researchName").getAsString();
				// researcherId = research.get("researcherId").getAsInt();
				researcherName = research.get("researcherName").getAsString();
				researchCategory = research.get("researchCategory").getAsString();
				researchDescription = research.get("researchDescription").getAsString();
				researchCost = research.get("researchCost").getAsFloat();
				researchDuration = research.get("researchDuration").getAsInt();
				startDate = research.get("startDate").getAsString();

				if (researchServiceModel.insertResearchProject(researchID, researchName, researcherId, researcherName,
						researchCategory, researchDescription, researchCost, researchDuration, startDate)) {
					result.addProperty("status", "done");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			result.addProperty("status", "error");
		}

		return result.toString();
	}

	@GET
	@Path("/getResearchProjects")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllresearchProjects(@DefaultValue("") @QueryParam("key") String key)// (String researchData)
	{
		// JsonObject itemObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// String key = itemObject.get("key").getAsString();

		// request validation
		if (!requestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}

		return researchServiceModel.getAllResearchProjects();
	}

	@GET
	@Path("/getResearchProject")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getresearchProjects(@DefaultValue("0") @QueryParam("researchID") Integer research,
			@DefaultValue("") @QueryParam("key") String key)// (String researchData)
	{
		// JsonObject itemObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// String key = itemObject.get("key").getAsString();

		// JsonObject researhObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// int researchID = researhObject.get("researchID").getAsInt();

		// request validation
		if (!requestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}

		return researchServiceModel.getResearchProject(research);
	}

	@GET
	@Path("/searchResearchProjects")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String searchResearch(@DefaultValue("") @QueryParam("researchName") String research,
			@DefaultValue("") @QueryParam("key") String key)// (String researchData)
	{
		// JsonObject itemObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// String key = itemObject.get("key").getAsString();

		// JsonObject researhObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// String researchName = researhObject.get("researchName").getAsString();

		// request validation
		if (!requestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}

		return researchServiceModel.searchResearchProjects(research);
	}

	@GET
	@Path("/searchResearchProjectsCat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String searchResearchByCategory(@DefaultValue("") @QueryParam("researchCategory") String research,
			@DefaultValue("") @QueryParam("key") String key)// (String researchData)
	{
		// JsonObject itemObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// String key = itemObject.get("key").getAsString();

		// JsonObject researhObject = new
		// JsonParser().parse(researchData).getAsJsonObject();
		// String researchCategory =
		// researhObject.get("researchCategory").getAsString();

		// request validation
		if (!requestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}

		return researchServiceModel.searchResearchProjectsByCategory(research);
	}

	@PUT
	@Path("/updateresearchProject")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateResearch(String researchData) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error");

		JsonObject research = new JsonParser().parse(researchData).getAsJsonObject();

		// request validation
		if (!requestValidator.validate(research.get("key").getAsString())) {

			return result.toString();
		}

		JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();

		int researchID = researhObject.get("researchID").getAsInt();
		String researchName = researhObject.get("researchName").getAsString();
		int researcherId = researhObject.get("researcherId").getAsInt();
		String researcherName = researhObject.get("researcherName").getAsString();
		String researchCategory = researhObject.get("researchCategory").getAsString();
		String researchDescription = researhObject.get("researchDescription").getAsString();
		float researchCost = researhObject.get("researchCost").getAsFloat();
		int researchDuration = researhObject.get("researchDuration").getAsInt();
		String startDate = researhObject.get("startDate").getAsString();

		return researchServiceModel.updateResearchProject(researchID, researchName, researcherId, researcherName,
				researchCategory, researchDescription, researchCost, researchDuration, startDate);

	}

	@DELETE
	@Path("/deleteresearchProject")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteResearch(String researchData) {
		String returnValue = "failed";

		JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();
		String key = researhObject.get("key").getAsString();

		// request validation
		if (!requestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}

		int researchID = researhObject.get("researchID").getAsInt();

		return researchServiceModel.deleteResearchProject(researchID);

	}

}
