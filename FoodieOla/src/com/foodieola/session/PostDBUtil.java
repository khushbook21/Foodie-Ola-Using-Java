package com.foodieola.session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PostDBUtil {

private DataSource dataSource;
	
	public PostDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public void addPost(PostBluePrint newPost) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO `post` "
					   + "(`username`, `foodtype`, `No_of_plate`, `picking_type`, `delete_at`, `logitude`, `latitude`, `fixed_pos`) "
					   + "values (?, ?, ?, ?, DATE_ADD(now(),interval 1 day), ?, ?, ?)";
            //INSERT INTO `post` 
			// (`username`, `foodtype`, `No_of_plate`, `picking_type`, `delete_at`, `logitude`, `latitude`,
			//`fixed_pos`) VALUES ('samina', 'any', '2', 'delivery', DATE_ADD(now(),interval 1 day), '73.09234', '91.192345', '1')
//			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, newPost.getUsername());
			myStmt.setString(2, newPost.getFoodType());
			myStmt.setString(3, newPost.getNoOfPlate());
			myStmt.setString(4, newPost.getPickingType());
			myStmt.setString(5, newPost.getLongitude());
			myStmt.setString(6, newPost.getLatitude());
			myStmt.setString(7, newPost.getFixed());
			
			// execute sql insert
			myStmt.execute();
			System.out.println("Query executed");
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	
	
	
	//close section
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

	public List<needAroundYouBluePrint> getNeed(BoundingBoxBluePrint backingJavaObj) throws Exception {
		
		List<needAroundYouBluePrint> needAroundYouList = new ArrayList<>();
		System.out.println("in getneed");
		
		Connection myConn = null;
		ResultSet myRs = null,myRs2=null;
		Statement Stmt = null, Stmt2=null;

		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			System.out.println(backingJavaObj.getLatUp());
			// create sql statement
			String sql = "Select post.id,post.username,post.No_of_plate,post.foodtype,post.picking_type,"
					+ "post.created_at,people.phone,post.latitude,post.logitude from post "
					+ "INNER JOIN people ON post.username=people.username "
					+ "WHERE post.delete_at > DATE_SUB(now(),interval 1 day) and post.fixed_pos='FixLoc' AND (post.latitude BETWEEN "+backingJavaObj.getLatDown().toString()+" AND "+backingJavaObj.getLatUp().toString()+" ) AND "
					+ "(post.logitude BETWEEN "+backingJavaObj.getLongDown().toString()+" AND "+backingJavaObj.getLongUp().toString()+") order by post.created_at";
			
			Stmt = myConn.createStatement();
			
			// execute query
			myRs = Stmt.executeQuery(sql);
			
			System.out.println("Query executed "+myRs);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String username = myRs.getString("username");
				String noOfPlate = String.valueOf(myRs.getInt("No_of_plate"));
				String foodType = myRs.getString("foodtype");
				String pickType = myRs.getString("picking_type");
				String createdAt = myRs.getString("created_at");
				String phone=myRs.getString("phone");
				String latitude=String.valueOf(myRs.getDouble("latitude"));
				String logitude=String.valueOf(myRs.getDouble("logitude"));
				
				
				// create new student object
				needAroundYouBluePrint tempneed = new needAroundYouBluePrint(id, username, noOfPlate, foodType,pickType,createdAt,phone,latitude,logitude);
				System.out.println("for loop "+id);
				// add it to the list of students
				needAroundYouList.add(tempneed);				
			}
			// Fetching the dynamic location
			
			String sql2 = "Select post.id,post.username,post.No_of_plate,post.foodtype,post.picking_type,"
					+ "post.created_at,people.phone,post.latitude,post.logitude from post "
					+ "INNER JOIN people ON post.username=people.username "
					+ "WHERE post.delete_at > DATE_SUB(now(),interval 1 day) and post.fixed_pos='DymLoc' AND (post.latitude BETWEEN "+backingJavaObj.getLatDown().toString()+" AND "+backingJavaObj.getLatUp().toString()+" ) AND "
					+ "(post.logitude BETWEEN "+backingJavaObj.getLongDown().toString()+" AND "+backingJavaObj.getLongUp().toString()+") order by post.created_at";
			
			Stmt2 = myConn.createStatement();
			
			// execute query
			myRs2 = Stmt2.executeQuery(sql2);
			
			System.out.println("Query executed for Dynamic Location "+myRs2);
			
			// process result set
			while (myRs2.next()) {
				
				// retrieve data from result set row
				int id = myRs2.getInt("id");
				String username = myRs2.getString("username");
				String noOfPlate = String.valueOf(myRs2.getInt("No_of_plate"));
				String foodType = myRs2.getString("foodtype");
				String pickType = myRs2.getString("picking_type");
				String createdAt = myRs2.getString("created_at");
				String phone=myRs2.getString("phone");
				String latitude=String.valueOf(myRs2.getDouble("latitude"));
				String logitude=String.valueOf(myRs2.getDouble("logitude"));
				
				
				// create new student object
				needAroundYouBluePrint tempneed2 = new needAroundYouBluePrint(id, username, noOfPlate, foodType,pickType,createdAt,phone,latitude,logitude);
				System.out.println("for loop "+id);
				// add it to the list of students
				needAroundYouList.add(tempneed2);				
			}
			
			
			
			System.out.println("in getneed function "+needAroundYouList);
			return needAroundYouList;	
			
		}
		finally {
			// close JDBC objects
			close(myConn, Stmt, myRs);
		}
	
	}

	public boolean updateGiver(String backingJavaObj, String string) throws Exception {
		
		Connection myConn = null, myConn2 = null, myConn3 = null;
		PreparedStatement myStmt = null,myStmt2 = null,myStmt3 = null;
		ResultSet myRs2 = null, myRs3 = null;
		boolean SameAsGiver=false, InRelation=false; 
		try {
			// get db connection
			myConn2 = dataSource.getConnection();
			
			// create SQL update statement
			String sql2 = "Select username from post where username=? and id=?";
			
			// prepare statement
			myStmt2 = myConn2.prepareStatement(sql2);
			
			// set params
			myStmt2.setString(1, string);
			myStmt2.setInt(2, Integer.parseInt(backingJavaObj));
			System.out.println(sql2);
			// execute SQL statement
			myRs2=myStmt2.executeQuery();
			
			if(myRs2.next()) {
				SameAsGiver=true;
				System.out.println("Giver Exist");
			}
			
			System.out.println("Query Execute for NotSameAs Giver "+SameAsGiver);
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		try {
			// get db connection
			myConn3 = dataSource.getConnection();
			
			// create SQL update statement
			String sql3 = "Select post_id from relation where giver=? and post_id=?";
			
			// prepare statement
			myStmt3 = myConn3.prepareStatement(sql3);
			
			// set params
			myStmt3.setString(1, string);
			myStmt3.setInt(2, Integer.parseInt(backingJavaObj));
			System.out.println(sql3);
			// execute SQL statement
			myRs3= myStmt3.executeQuery();
			
			if(myRs3.next()) {
				InRelation=true;
				System.out.println("relation does exist");
			}
			System.out.println("Query Execute fro notInRelation "+InRelation);
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
		// here the main update is going on
		if(InRelation==false && SameAsGiver==false)
		{
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "UPDATE post set giver=? where id=?";
			System.out.println(sql);
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, string);
			myStmt.setInt(2, Integer.parseInt(backingJavaObj));
			
			// execute SQL statement
			myStmt.execute();
			
			System.out.print("Query Execute");
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
		return true;
		}
		return false;
	}

	public List<MyGivingsBluePrint> getGiving(String string) throws Exception {
		
		List<MyGivingsBluePrint> myGiving = new ArrayList<>();
		System.out.println("In getGiving");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from relation where giver='"+string+"' order by created_at";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				String id = myRs.getString("post_id");
				String taker = myRs.getString("taker");
				String foodtype = myRs.getString("foodtype");
				String plate = myRs.getString("plate");
				String pick = myRs.getString("pick");
				String taker_accepted = myRs.getString("taker_accepted");
				String otp = myRs.getString("otp");
				String created_at=myRs.getString("created_at");
				
				// create new student object
				MyGivingsBluePrint tempGiving = new MyGivingsBluePrint(id, taker, foodtype, plate, pick, taker_accepted, otp, created_at);
				
				// add it to the list of students
				myGiving.add(tempGiving);				
			}
			
			return myGiving;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public List<MyTakingBluePrint> getTaking(String string) throws Exception {
		List<MyTakingBluePrint> myTaking = new ArrayList<>();
		System.out.println("In getTaking");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "SELECT id, username, foodtype, No_of_plate, picking_type, created_at from post where username='"+string+"'";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			System.out.println("printing the get result "+myRs);

			// process result set
			while (myRs.next()) {
				//  retrieve data from result set row
				String id = myRs.getString("id");
				String username = myRs.getString("username");
				String foodtype = myRs.getString("foodtype");
				String No_of_plate = myRs.getString("No_of_plate");
				String picking_type = myRs.getString("picking_type");
				String created_at = myRs.getString("created_at");
				
				// create new student object
				MyTakingBluePrint tempTaking = new MyTakingBluePrint(username, No_of_plate, picking_type, foodtype, created_at, id);
				
				// add it to the list of students
				myTaking.add(tempTaking);				
			}
			
			return myTaking;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		
	}

	public List<MyOTPBluePrint> getOTP(String string) throws Exception {
		
		List<MyOTPBluePrint> myOTP = new ArrayList<>();
		System.out.println("In getOTP");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from relation where taker='"+string+"' and taker_accepted=1";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			

			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				String OTP = myRs.getString("otp");
				String giver = myRs.getString("giver");
				String created_At = myRs.getString("created_at");
				
				// create new student object
				MyOTPBluePrint tempOTP = new MyOTPBluePrint(OTP, giver, created_At);
				
				// add it to the list of students
				myOTP.add(tempOTP);				
			}
			
			return myOTP;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}

	public List<MyPostInfoBluePrint> getPostInfo(String postId, String string) throws Exception {

		List<MyPostInfoBluePrint> myPostInfo = new ArrayList<>();
		System.out.println("In getPostInfo");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "Select relation.post_id, relation.taker, relation.plate, relation.foodtype, people.phone, relation.pick, people.username, relation.created_at"
					+ " from relation"
					+ " LEFT JOIN people"
					+ " on relation.giver=people.username"
					+ " WHERE relation.post_id="+postId+" and relation.taker='"+string+"' order by relation.created_at";
			System.out.print(sql);
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				String taker = myRs.getString("taker");
				String plate = myRs.getString("plate");
				String foodtype = myRs.getString("foodtype");
				String phone = myRs.getString("phone");
				String pick = myRs.getString("pick");
				String username = myRs.getString("username");
				String created_at = myRs.getString("created_at");
				
				// create new student object
				MyPostInfoBluePrint tempMyPostInfo = new MyPostInfoBluePrint(taker, plate, foodtype, phone, pick, username, created_at);
				
				// add it to the list of students
				myPostInfo.add(tempMyPostInfo);				
			}
			
			return myPostInfo;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
	}

	public void approveThePost(String postId, String giver) throws Exception {
		
		Connection myConn = null, myConn2=null;
		PreparedStatement myStmt = null, myStmt2=null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			System.out.println(giver);
			// create SQL update statement
			String sql = "UPDATE relation set taker_accepted=1 where post_id="+postId+" and giver="+giver;
		
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			System.out.println(sql);
			// execute SQL statement
			myStmt.execute();
			
			System.out.print("Query Execute");
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
		
		try {
			// get db connection
			myConn2 = dataSource.getConnection();
			System.out.println(giver);
			// create SQL update statement
			String sql = "DELETE FROM relation WHERE relation.post_id="+ postId+ " and giver <> " +giver;
		
			// prepare statement
			myStmt2 = myConn2.prepareStatement(sql);
			
			// set params
			System.out.println(sql);
			// execute SQL statement
			myStmt2.execute();
			
			System.out.print("Query Execute");
		}
		finally {
			// clean up JDBC objects
			close(myConn2, myStmt2, null);
		}
	}

	public void deleteRelation(String postId, String giver) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete student
			String sql = "delete from relation where post_id=? and giver=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, postId);
			myStmt.setString(2, giver);
			
			System.out.println(sql);
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}

	public boolean checkOtp(String postIdCleanedAccept, String otpEntered, String string) throws Exception{
		
		System.out.println("In checkOtp");
		Connection myConn = null,myConn2 = null;
		Statement myStmt = null,myStmt2 = null;
		ResultSet myRs = null;
		boolean checkOk=false;
		boolean recordDeletedFromRelation=false;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "Select otp from relation where otp="+otpEntered+" AND post_Id="+postIdCleanedAccept+" And giver='"+string+"'";
			System.out.print(sql);
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			System.out.println("Query For Check on OTP executed");

			// process result set
			if (myRs.next()) {
				// retrieve data from result set row
				checkOk=true;		
			}
			
					
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		if(checkOk==true) {
			try {
				// get a connection
				myConn2 = dataSource.getConnection();
				
				// create sql statement
				String sql = "DELETE from relation where otp="+otpEntered+" AND post_Id="+postIdCleanedAccept+" And giver='"+string+"'";
				System.out.print(sql);
				myStmt2 = myConn2.createStatement();
				
				// execute query
				myStmt2.execute(sql);
				
				System.out.println("Query For delete on OTP executed");
				
				recordDeletedFromRelation=true;
						
			}
			finally {
				// close JDBC objects
				close(myConn, myStmt, myRs);
			}
		}
		
		if(recordDeletedFromRelation==true) {
			return true;
		}
		else{
				return false;
		}
		
	}

	public ProfileBluePrint getProfileInfo(String string) throws Exception {
		
		System.out.println("In getProfileInfo");
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		ProfileBluePrint profileBlurPrint=null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "Select username,phone, latitude, longitude,email_id from people where username='"+string+"'";
			System.out.print(sql);
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			System.out.println("Query For Get Usr Info");
			
			if(myRs.next()) {
				String username= myRs.getString("username");
				String email= myRs.getString("email_id");
				String latitude= myRs.getString("latitude");
				String longitude= myRs.getString("longitude");
				String phone= myRs.getString("phone");
				profileBlurPrint= new ProfileBluePrint(username, email, latitude, longitude, phone);
			}
					
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		
		return profileBlurPrint;
	}
		
	
}
