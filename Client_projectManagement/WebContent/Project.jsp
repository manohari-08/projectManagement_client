<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Project.js"></script>
</head>
<body>
<h3 style="text-align:center;">Project Management</h3>
<div class="container"><div class="row">
	<div class="col-6">
	 

	<form id="formItem" name="formItem">
	
		Project Title:
		<input id="ProjTitle" name="ProjTitle" type="text" class="form-control form-control-sm" placeholder = "Enter Title"><br>
		
		Project description:
		<input id="projDesc" name="projDesc" type="text" class="form-control form-control-sm" placeholder = "Enter Project Description"><br> 
		
		Project location:
		<input id="location" name="location" type="text" class="form-control form-control-sm" placeholder = "Enter project location"><br>
		
		Project Sector:
		<input id="sector" name="sector" type="text" class="form-control form-control-sm" placeholder = "Enter Sector"><br>
		
		Project Owner:
		<input id="projOwner" name="projOwner" type="text" class="form-control form-control-sm" placeholder = "Enter owner name"><br>
		
		Project stage :
		<select id="projStage" name="projStage" type="text" class="form-control form-control-sm" placeholder = "Select stage">
		
		    <option value="Research">Research</option>
		    <option value="Implementation">Implementation</option>
		    <option value="Test and Transition">Test and Transition</option>
		</select><br>
		
		Project Budget:
		<input id="projectBudget" name="projectBudget" type="text" class="form-control form-control-sm" placeholder = "Enter Budget"><br>
		
		
		
		
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	
	
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
	<%
	 Project projObj = new Project(); 
	 out.print(projObj.readProj()); 
	%>
	</div>
</div> </div> </div> 

</body>
</html>