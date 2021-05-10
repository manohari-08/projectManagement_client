$(document).on("click", "#btnSave", function(event){ 
	
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
 
	 
	// Form validation-------------------
	var status = validateItemForm(); 
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 
 return; 
} 


// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	$.ajax( 
	{ 
	 url : "ProjectAPI", 
	 type : type, 
	 data : $("#formItem").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) { 
		 
		 onItemSaveComplete(response.responseText, status); 
	 } 
	}); 
});

function onItemSaveComplete(response, status){ 
	if (status == "success") {
		
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") { 
			 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divItemsGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") {
			 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
	} 
	else if (status == "error") { 
		
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
	} else{ 
		
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
		}
	$("#hidItemIDSave").val(""); 
	$("#formItem")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event){ 
		
		 $("#hidItemIDSave").val($(this).data("userid")); 
		 $("#ProjTitle").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#projDesc").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#location").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#sector").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#projOwner").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#projStage").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#projectBudget").val($(this).closest("tr").find('td:eq(6)').text()); 
});





$(document).on("click", ".btnRemove", function(event) { 
	 $.ajax( 
	 { 
	 	url : "ProjectAPI", 
	 	type : "DELETE", 
	 	data : "ProjId=" + $(this).data("userid"),
	 	dataType : "text", 
	 	complete : function(response, status) { 
	 		onItemDeleteComplete(response.responseText, status); 
	 	} 
	}); 
})
	


function onItemDeleteComplete(response, status){
	
	if (status == "success") {
		
		var resultSet = JSON.parse(response); 
			if (resultSet.status.trim() == "success"){
			
				$("#alertSuccess").text("Successfully deleted."); 
				$("#alertSuccess").show(); 
				$("#divItemsGrid").html(resultSet.data); 
				
			} else if (resultSet.status.trim() == "error") { 
				
				$("#alertError").text(resultSet.data); 
				$("#alertError").show(); 
		} 
	} 
	else if (status == "error") { 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} 
	else { 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}

// CLIENT-MODEL================================================================
function validateItemForm(){
	// CODE
	if ($("#ProjTitle").val().trim() == "")
	{
	return "Insert Project Title.";
	}
	// NAME
	if ($("#projDesc").val().trim() == "")
	{
	return "Insert project Desc.";
	}
	
	if ($("#location").val().trim() == "")
	{
	return "Insert location.";
	}
	
	if ($("#sector").val().trim() == "")
	{
	return "Insert sector.";
	}
	
	if ($("#projOwner").val().trim() == "")
	{
	return "Insert project Owner.";
	}
	
	if ($("#projStage").val().trim() == "")
	{
	return "Insert project project Stage.";
	}
	
// PRICE-------------------------------
if ($("#projectBudget").val().trim() == ""){
	
	return "Insert Item Price.";
}
// is numerical value
var tmpPrice = $("#projectBudget").val().trim();
if (!$.isNumeric(tmpPrice)){
			
	return "Insert a numerical value for budget.";
}
		
// convert to decimal price
$("#projectBudget").val(parseFloat(tmpPrice).toFixed(2));


	return true;
}