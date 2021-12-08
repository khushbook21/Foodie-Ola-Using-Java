package com.foodieola.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.Statement;
/**
 * Servlet implementation class testJdbc
 */
@WebServlet("/testJdbc")
public class testJdbc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource/connection pool for Resource Injection
		@Resource(name="jdbc/foodieola2")
		private DataSource dataSource;

    /**
     * Default constructor. 
     */
    public testJdbc() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Step 1:  Set up the printwriter
				PrintWriter out = response.getWriter();
				response.setContentType("text/plain");
				
				// Step 2:  Get a connection to the database
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRs = null;
				
				try {
					myConn = dataSource.getConnection();
					System.out.println("its working");
					
					// Step 3:  Create a SQL statements
					String sql = "select * from people";
					myStmt = myConn.createStatement();
					
					// Step 4:  Execute SQL query
					myRs = myStmt.executeQuery(sql);
					
					// Step 5:  Process the result set
					while (myRs.next()) {
						String email = myRs.getString("username");
						out.println(email);
					}
				}
				catch (Exception exc) {
					exc.printStackTrace();
				}
		
		
		
	}

}
