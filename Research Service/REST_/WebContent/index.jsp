<%@page import="com.ResearchService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="Views/bootstrap.min.css">
    <script src="Components/jquery-3.6.0.js"></script>
    <script src="Components/main.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<form id="formResearch" name="formResearch">
<input id="researchID" name="researchID" type="hidden" value = "0">
<input id="researcherId" name="researcherId" type="hidden" value = "1">
Research Name:<input id="researchName" name="researchName" type="text"class="form-control form-control-sm">
<br>
Researcher Name:<input id="researcherName" name="researcherName" type="text"class="form-control form-control-sm">
<br>
Research Category:<input id="researchCategory" name="researchCategory" type="text"class="form-control form-control-sm">
<br>
Research Description:<input id="researchDescription" name="researchDescription" type="text"class="form-control form-control-sm">
<br>
Research Cost:<input id="researchCost" name="researchCost" type="text"class="form-control form-control-sm">
<br>
Research Duration:<input id="researchDuration" name="researchDuration" type="text"class="form-control form-control-sm">
<br>
Start Date:<input id="startDate" name="startDate" type="text"class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
<input type="hidden" id="hidResearchIDSave" name="hidResearchIDSave" value="">

</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>


<br>

<div id="divResearchesGrid">
 <%
 ResearchService researchObj = new ResearchService();
 out.print(researchObj.getAllResearchProjects()); 
 %>
</div>


</body>

</html>