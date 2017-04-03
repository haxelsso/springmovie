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

// The MoviesController program 
// author Helena Axelsson
// version 1.0


@Controller
public class MoviesController {

	@GetMapping("/movies")
	public String index(@RequestParam(value="name", required=false, defaultValue="") String result, Model model) {

		// template processing
		model.addAttribute("listofmovies", this.getMoviesFromDb());
	
		// template processing
		return "movies";
	}

	@GetMapping("/api/v1/movies")
	public @ResponseBody List index_json(@RequestParam(value="name", required=false, defaultValue="") String result) {

		// JSON processing
		return this.getMoviesFromDb();
	}

	@PostMapping("/movies")

	public String moviesSubmit(@RequestParam(value="id", required=false, defaultValue="") String id,
				   @RequestParam(value="name", required=false, defaultValue="") String movieName,
				   @RequestParam(value="year", required=false, defaultValue="") String movieYear, Model model) {

		// JDBC Name and database URL
		String JDBC_DRIVER = "org.postgresql.Driver";
		String DB_URL = "jdbc:postgresql://localhost/moviedb?user=postgres&password=postgres&ssl=true";

		String INSERT_INTO_MOVIE = "INSERT INTO MOVIE (movieid, name, year) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;


		try {
			Class.forName(JDBC_DRIVER);
			// Open a connection 
			conn = DriverManager.getConnection(DB_URL);

			conn.setAutoCommit(false);			

			// Execute SQL Query
			stmt = conn.prepareStatement(INSERT_INTO_MOVIE);

			stmt.setInt(1, Integer.parseInt(id));
			stmt.setString(2, movieName);
			stmt.setInt(3, Integer.parseInt(movieYear));

			stmt.executeUpdate();	
			
			//Clean-up environment
			stmt.close();
			conn.commit();
			conn.close();

		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			// Handle errors for Class.forName
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

		System.out.println("Records created successfully");
		// template processing
		model.addAttribute("listofmovies", this.getMoviesFromDb());
		// template processing
		return "movies";
	}
	
	private List getMoviesFromDb() {
	
		// JDBC Name and database URL
		String JDBC_DRIVER = "org.postgresql.Driver";
		String DB_URL = "jdbc:postgresql://localhost/moviedb?user=postgres&password=postgres&ssl=true";

		String SELECT_ALL = "SELECT movieid, name, year FROM movie ORDER BY movieid ASC";

		//String forename;
		String id;
		String movieName;
		String movieYear;

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

				Movie m = new Movie();
				
				// movieId
				id = rs.getString("movieid");
				if(rs.wasNull() || id.length()==0) {
					id = "unknown movieid";
				}
				m.setMovieId(id);
				

				// movieName
				movieName = rs.getString("name");
				if(rs.wasNull() || movieName.length()==0) {
					movieName = "unknown movieName";
				}
				m.setName(movieName);
				

				// movieYear
				movieYear = rs.getString("year");
				if(rs.wasNull() || movieYear.length()==0) {
					movieYear = "unknown movieYear";
				}
				m.setYear(movieYear);
				
				myList.add(m);
				
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
