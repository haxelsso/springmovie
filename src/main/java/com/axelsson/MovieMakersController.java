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

// The MovieMakersController program 
// author Helena Axelsson
// version 1.0


@Controller
public class MovieMakersController {

	@GetMapping("/moviemakers")
	public String index(@RequestParam(value="name", required=false, defaultValue="") String result, Model model) {

		// template processing
		model.addAttribute("listofmoviemakers", this.getMovieMakersFromDb());
	
		// template processing
		return "moviemakers";
	}

	@GetMapping("/api/v1/moviemakers")
	public @ResponseBody List index_json(@RequestParam(value="name", required=false, defaultValue="") String result) {

		// JSON processing
		return this.getMovieMakersFromDb();
	}


	private List getMovieMakersFromDb() {

		// JDBC Name and database URL
		String JDBC_DRIVER = "org.postgresql.Driver";
		String DB_URL = "jdbc:postgresql://localhost/moviedb?user=postgres&password=postgres&ssl=true";
	
		String SELECT_ALL = "SELECT makerid, makerforename, makerlastname FROM moviemaker ORDER BY makerid ASC";

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

				MovieMaker mm = new MovieMaker();
				
				//Retrieve by column name
				
				// makerId
				id = rs.getString("makerid");
				if(rs.wasNull() || id.length()==0) {
					id = "unknown makerId";
				}
				mm.setMakerId(id);
				

				// makerforename
				foreName = rs.getString("makerforename");
				if(rs.wasNull() || foreName.length()==0) {
					foreName = "unknown makerforename";
				}
				mm.setMakerForeName(foreName);
				

				// makerlastname
				lastName = rs.getString("makerlastname");
				if(rs.wasNull() || lastName.length()==0) {
					lastName = "unknown makerlastname";
				}
				mm.setMakerLastName(lastName);
				
				myList.add(mm);

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
