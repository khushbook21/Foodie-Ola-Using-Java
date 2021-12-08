package com.foodieola.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class SessionDBUtil {

	private DataSource dataSource;
	
	public SessionDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	
	public void addUser(RegisterBluePrint newUser) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into people "
					   + "(`username`, `email_id`, `phone`, `password`, `longitude`, `latitude`) "
					   + "values (?, ?, ?, ?, ?, ?)";
			// INSERT INTO `people` (`username`, `email_id`, `phone`, `password`, `logitude`, `latitude`,)
			// VALUES ('amanoddin', 'amanoddin@gmail.com', '9820434120', 'qwerty', '19.01894', '73.45978');
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, newUser.getUsername());
			myStmt.setString(2, newUser.getEmail());
			myStmt.setString(3, newUser.getPhone());
			myStmt.setString(4, newUser.getPassword());
			myStmt.setString(5, newUser.getLongitude());
			myStmt.setString(6, newUser.getLatitude());
			
			// execute sql insert
			myStmt.execute();
			System.out.println("Query executed");
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}


	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}


	public boolean checkExist(String username, String password) throws Exception  {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "select username from people where username=? and password=?;";
					
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			// execute sql insert
			myRs = myStmt.executeQuery();
			System.out.println("Query executed");
			// retrieve data from result set row
			
			if (myRs.next()) {
								
				return true;
			}
			else {
				return false;
			}				
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}


	public void updateLatLong(String latitude, String longitude,String username) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "UPDATE people SET latitude=?,longitude=? WHERE username=?";
					
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, latitude);
			myStmt.setString(2, longitude);
			myStmt.setString(3, username);
		
			// execute sql insert
			myStmt.execute();
			System.out.println("Query executed");
						
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
	}
}
