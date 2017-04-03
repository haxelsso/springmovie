package com.axelsson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// template processing
import org.springframework.ui.Model;

//jdbc classes
import java.sql.*;
import org.postgresql.*;

import java.util.*;

// The ActorsController program 
// author Helena Axelsson
// version 1.0


@Controller
public class ActorsController {

	@GetMapping("/actors")
	public String index(@RequestParam(value="name", required=false, defaultValue="") String result, Model model) {

		// template processing
		model.addAttribute("listofactors", this.getActorsFromDb());
	
		// template processing
		return "actors";
	}

	@GetMapping("/api/v1/actors")
	public @ResponseBody List index_json(@RequestParam(value="name", required=false, defaultValue="") String result) {

		// JSON processing
		return this.getActorsFromDb();
	}


	private List getActorsFromDb() {

		// JDBC Name and database URL
		String JDBC_DRIVER = "org.postgresql.Driver";
		String DB_URL = "jdbc:postgresql://localhost/moviedb?user=postgres&password=postgres&ssl=true";
		
		String SELECT_ALL = "SELECT actorid, actorforename, actorlastname FROM actor ORDER BY actorid ASC";

		//String forename;
		String id;
		String foreName;
		String lastName;

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List myList = new ArrayList();

		try {
			Class.forName(JDBC_DRIVER);
			// Open a connection 			
			conn = DriverManager.getConnection(DB_URL);			

			// Execute SQL Query
			stmt = conn.createStatement();

			rs = stmt.executeQuery(SELECT_ALL);
			
			//Extract data from result set
			while(rs.next()){
				
				Actor a = new Actor();
				
				//Retrieve by column name
				
				// actorid
				id = rs.getString("actorid");
				if(rs.wasNull() || id.length()==0) {
					id = "unknown actorid";
				}
				a.setActorId(id);
				

				// actorforename
				foreName = rs.getString("actorforename");
				if(rs.wasNull() || foreName.length()==0) {
					foreName = "unknown actorforename";
				}
				a.setActorForeName(foreName);
				

				// actorlastname
				lastName = rs.getString("actorlastname");
				if(rs.wasNull() || lastName.length()==0) {
					lastName = "unknown actorlastname";
				}
				a.setActorLastName(lastName);
				
				myList.add(a);
				
			}
			//Clean-up environment
			rs.close();
			stmt.close();
			conn.close();

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();			
			}catch(SQLException se2){
			} //nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
					se.printStackTrace();
			}// end finally try
			
		} // end try

	return myList;
	}
}
