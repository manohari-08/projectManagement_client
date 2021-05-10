package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {
	
	
		
		//A common method to connect to the DB
		private Connection connect(){
			Connection con = null;
			try{
				Class.forName("com.mysql.jdbc.Driver");

				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_client", "root", "");
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
			return con;
		}
			
		
		
		//Insert Project Details
		public String insertProj(String ProjTitle, String projDesc,String location,String sector , String projOwner, String projStage, String projectBudget){
			String output = "";
			try{
					Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for inserting."; 
				}
				
					
					// create a prepared statement
					String query = " insert into project(`ProjId`,`ProjTitle`,`projDesc`,`location`,`sector`,`projOwner`,`projStage`,`projectBudget`)"+ " values (?, ?, ?,?,?,?,?,?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, ProjTitle);
					 preparedStmt.setString(3, projDesc);
					 preparedStmt.setString(4, location);
					 preparedStmt.setString(5, sector);
					 preparedStmt.setString(6, projOwner);
					 preparedStmt.setString(7, projStage);
					 preparedStmt.setDouble(8, Double.parseDouble(projectBudget)); 
					 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newProj = readProj(); 
					 output = "{\"status\":\"success\", \"data\": \"" + newProj + "\"}";
					 
					 }catch (Exception e)
					 {
						 
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the project.\"}"; 
						 System.err.println(e.getMessage());
					 }
			 return output;
		 }
		
		
		
		
		public String readProj(){
			String output = "";
			try{
					Connection con = connect();
					if (con == null){
						return "Error while connecting to the database for reading."; 
			}
					
				// Prepare the html table to be displayed
				output = 
						"<table border='1' >"+ 
						"<tr >" +
							 "<th >Project Title</th>" +
							 "<th >project Description</th>" +
							 "<th>project Location</th>" +
							 "<th >project Sector</th>" +
							 "<th>project Owner</th>" +
							 "<th>project Stage</th>" +
							 "<th >project Budget</th>" +
							 "<th >Update</th>"+
							 "<th >Remove</th>"+ 
						 "</tr>";
	
				 String query = "select * from project";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 
				 // iterate through the rows in the result set
				 while (rs.next()){
					 
					 
					 String ProjId =  Integer.toString(rs.getInt("ProjId"));
					 String ProjTitle = rs.getString("ProjTitle");
					 String projDesc = rs.getString("projDesc");
					 String location = rs.getString("location");
					 String sector = rs.getString("sector");
					 String projOwner = rs.getString("projOwner");
					 String projStage = rs.getString("projStage");
					 String projectBudget = Double.toString(rs.getDouble("projectBudget"));
	
					 
					 // Add into the html table
					 
					 output += "<tr><td>" + ProjTitle + "</td>";
					 output += "<td>" + projDesc + "</td>";
					 output += "<td>" + location + "</td>";
					 output += "<td>" + sector + "</td>";
					 output += "<td>" + projOwner + "</td>";
					 output += "<td>" + projStage + "</td>";
					 output += "<td>" + projectBudget + "</td>";
		
					 
					 
					 // buttons
					
					 output += "<td><input name='btnUpdate' type='button' value='Update' "
								+ "class='btnUpdate btn btn-secondary' data-userid='" + ProjId + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' "
								+ "class='btnRemove btn btn-danger' data-userid='" + ProjId + "'></td></tr>"; 
				 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 
			 
			 }catch (Exception e){
				 
				 output = "Error while reading the Projects.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 
		}
		
		
		
		public String updateProject(String ProjId, String ProjTitle, String projDesc,String location,String sector , String projOwner, String projStage, String projectBudget){ 
			String output = ""; 
			try{
				Connection con = connect(); 
				if (con == null){
					return "Error while connecting to the database for updating."; 
				} 
				
				 // create a prepared statement
				 String query = "UPDATE project SET ProjTitle=?,projDesc=?,location=?,sector=?,projOwner=?,projStage=?,projectBudget=? WHERE ProjId=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				  
				 preparedStmt.setString(1, ProjTitle); 
				 preparedStmt.setString(2, projDesc); 
				 preparedStmt.setString(3, location);
				 preparedStmt.setString(4, sector);
				 preparedStmt.setString(5, projOwner);
				 preparedStmt.setString(6, projStage);
				 preparedStmt.setDouble(7, Double.parseDouble(projectBudget)); 
				 preparedStmt.setInt(8, Integer.parseInt(ProjId));
				 
 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newProj = readProj(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newProj + "\"}";
				 
		
				 } catch (Exception e) {
					 
					 output = "{\"status\":\"error\", \"data\": \"Error while updating the project.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
		 }
		
		

		public String deleteProject(String ProjId) { 
			String output = ""; 
			try{ 
				Connection con = connect(); 
				if (con == null) { 
					return "Error while connecting to the database for deleting."; 
				} 
					// create a prepared statement
					String query = "delete from project where ProjId=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(ProjId)); 
					
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					
					String newUser = readProj(); 
					output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}"; 
					
			} catch (Exception e) { 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		}
		
}


