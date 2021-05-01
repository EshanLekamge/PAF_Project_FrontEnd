$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts--------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide(); 

	// Form validation-------------------
	//var status = validateItemForm(); 
	//if (status != true) 
 	//{ 
 		//$("#alertError").text(status); 
 		//$("#alertError").show(); 
 		//return; 
 	//} 

	// If valid------------------------
	
	var type = ($("#hidResearchIDSave").val() == "") ? "POST" : "PUT"; 
 	$.ajax( 
 	{ 
 		url : "ResearchServlet", 
 		type : type, 
 		data : $("#formResearch").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onItemSaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onItemSaveComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{ 
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show(); 
			$("#divResearchesGrid").html(resultSet.data);
		} 
		else if (resultSet.status.trim() == "error") 
		{ 
			$("#alertError").text(resultSet.data); 
			$("#alertError").show();
		} 
	} 
	else if (status == "error") 
	{ 
		$("#alertError").text("Error while saving."); 
		$("#alertError").show(); 
	} 
	else
	{ 
		$("#alertError").text("Unknown error while saving.."); 
		$("#alertError").show(); 
	}
	
	$("#hidResearchIDSave").val(""); 
	$("#formResearch")[0].reset(); 
	
}

$(document).on("click", ".btnUpdate", function(event)
{ 
	$("#hidResearchIDSave").val($(this).data("researchid")); 
	$("#researchName").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#researcherName").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#researchCategory").val($(this).closest("tr").find('td:eq(4)').text()); 
	$("#researchDescription").val($(this).closest("tr").find('td:eq(5)').text()); 
	$("#researchCost").val($(this).closest("tr").find('td:eq(6)').text()); 
	$("#researchDuration").val($(this).closest("tr").find('td:eq(7)').text()); 
	$("#startDate").val($(this).closest("tr").find('td:eq(8)').text()); 
});

