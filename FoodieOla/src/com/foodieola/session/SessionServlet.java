package com.foodieola.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	HttpSession session;
    PrintWriter out;
	
   private SessionDBUtil sessionDBUtil;
   private PostDBUtil postDBUtil;
	
	@Resource(name="jdbc/foodieola2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			sessionDBUtil = new SessionDBUtil(dataSource);
			postDBUtil =new PostDBUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		out=response.getWriter();
		
		try {
			// read the "command" parameter
			String theCommand = findRoute(request, response);
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "insert";
			}
			//mainDashboardDispatcher
			// route to the appropriate method
			switch (theCommand) {
			
			case "insert":
				insertUser(request, response);
				break;
				
			case "signin":
				verifyUser(request, response);
				break;
				
			case "myProfileInfo":
				myProfileInfo(request, response);
				break;
				
			case "myOTP":
				myOTP(request, response);
				break;
				
			case "needAroundYou":
				needAroundYou(request, response);
				break;
				
			case "approveThePost":
				approveThePost(request, response);
				break;
				
			case "myPostInfo":
				myPostInfo(request, response);
				break;
			case "myTaking":
				myTaking(request, response);
				break;
			case "OtpThPost":
				otpThPost(request, response);
				break;
				
			case "post":
				addpost(request, response);
				break;
			case "myGiving":
				myGiving(request, response);
				break;
			case "acceptThePost":
				addGiver(request, response);
				break;
			case "deleteRelation":
				deleteRelation(request, response);
				break;
				
			default:
				insertUser(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	
	private void myProfileInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			session=request.getSession();
			System.out.println("just backed "+session.getAttribute("username").toString());
			Gson gson= new Gson();
			
			ProfileBluePrint profileInfo=postDBUtil.getProfileInfo(session.getAttribute("username").toString());
			System.out.println("Information Received");	
			
			String MyProfileInfo = gson.toJson(profileInfo);
			
			out.print(MyProfileInfo);
			
			
					
			}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
	}


	private void otpThPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		boolean recoredDeletedFromRelation=false;
			
		try {
			
			String jsonFromAjax = request.getParameter("OtpThPost");
			System.out.println(jsonFromAjax);
			JsonObject data = new JsonParser().parse(jsonFromAjax).getAsJsonObject();
			
			String postIdCleanedAccept = data.get("postIdCleanedDecline").toString();
			String otpEntered = data.get("otpEntered").toString();
			
			System.out.println("just backed "+otpEntered+ ' '+otpEntered);
			
			session=request.getSession();
			recoredDeletedFromRelation=postDBUtil.checkOtp(postIdCleanedAccept,otpEntered,session.getAttribute("username").toString());
			System.out.println("Update Complete for OTP");	
			
			if(recoredDeletedFromRelation==true) {
				out.print(true);
			}
			else { 
				out.print(false);
			}
			
			
					
			}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
	}


	private void deleteRelation(HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			String jsonFromAjax = request.getParameter("deleteRelation");
			System.out.println(jsonFromAjax);
			JsonObject data = new JsonParser().parse(jsonFromAjax).getAsJsonObject();
			
			String postId= data.get("postIdCleanedAccept").toString();
			session=request.getSession();
			System.out.println(session.getAttribute("username").toString());
			String giver=session.getAttribute("username").toString();
			System.out.println(postId);
			postDBUtil.deleteRelation(postId,giver);
			System.out.println(giver);	
			
			out.print(true);
			
		}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
		
		
	}


	private void approveThePost(HttpServletRequest request, HttpServletResponse response)throws Exception  {
		
		try {
			String jsonFromAjax = request.getParameter("approveThePost");
			System.out.println(jsonFromAjax);
			JsonObject data = new JsonParser().parse(jsonFromAjax).getAsJsonObject();
			
			String postId= data.get("postId").toString();
			String giver=data.get("giverName").toString();
			System.out.println(postId);
			postDBUtil.approveThePost(postId,giver);
			System.out.println(giver);	
			
			out.print(true);
			
		}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
		
	}


	private void myPostInfo(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		
		try {
			Gson gson=new Gson();
			String postId= request.getParameter("myPostInfo");
			System.out.println(postId);
			
			session=request.getSession();
			System.out.println(session.getAttribute("username").toString());
			
			List<MyPostInfoBluePrint> myPostInfo = postDBUtil.getPostInfo(postId,session.getAttribute("username").toString());
			System.out.println(myPostInfo);	
			String MyPostInfo = gson.toJson(myPostInfo);
			
			out.print(MyPostInfo);
			
		}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
	}


	private void myOTP(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
		try {
			Gson gson=new Gson();
			
			session=request.getSession();
			System.out.println(session.getAttribute("username").toString());
			
			List<MyOTPBluePrint> myOTP = postDBUtil.getOTP(session.getAttribute("username").toString());
			System.out.println(myOTP);	
			String MyOTP = gson.toJson(myOTP);
			
			out.print(MyOTP);
			
		}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
	}


	private void myTaking(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		try {
			Gson gson=new Gson();
			session=request.getSession();
			System.out.println(session.getAttribute("username").toString());
			List<MyTakingBluePrint> myTaking = postDBUtil.getTaking(session.getAttribute("username").toString());
			
			System.out.println(myTaking);	
			String MyTaking = gson.toJson(myTaking);
			
			out.print(MyTaking);
			
		}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
	}


	private void myGiving(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
		Gson gson=new Gson();

		session=request.getSession();
		System.out.println(session.getAttribute("username").toString());
		List<MyGivingsBluePrint> myGivings = postDBUtil.getGiving(session.getAttribute("username").toString());
		System.out.println(myGivings);	
		String MyGiving = gson.toJson(myGivings);
		
		out.print(MyGiving);
		
	}
	catch(JsonSyntaxException e) {
		out.print(e);;
	}
	}


	private void addGiver(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// get need from db util
		try {
			
		String jsonFromAjax = request.getParameter("acceptThePost");
		System.out.println(jsonFromAjax);
		JsonObject data = new JsonParser().parse(jsonFromAjax).getAsJsonObject();
		
		String backingJavaObj = data.get("postIdCleanedAccept").toString();
		System.out.println("just backed "+backingJavaObj);
		
		session=request.getSession();
		System.out.println(session.getAttribute("username").toString());
		boolean isUpdated= postDBUtil.updateGiver(backingJavaObj,session.getAttribute("username").toString());
		System.out.println("Update Complete"+ isUpdated);	
		
		out.print(isUpdated);
				
		}
	catch(JsonSyntaxException e) {
		out.print(e);;
	}
		
	}


	private void needAroundYou(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// get need from db util
		try {
			
		String jsonFromAjax = request.getParameter("needAroundYou");
		System.out.println(jsonFromAjax);
		
		Gson gson=new Gson();
		JsonObject data = new JsonParser().parse(jsonFromAjax).getAsJsonObject();
		
		BoundingBoxBluePrint backingJavaObj = new BoundingBoxBluePrint(data.get("longitudeUp").toString(), data.get("longitudeDown").toString(), data.get("latitudeUp").toString(), data.get("latitudeDown").toString());
		
		System.out.println("just backed "+backingJavaObj.getLatUp());
		List<needAroundYouBluePrint> needAroundYou = postDBUtil.getNeed(backingJavaObj);
		System.out.println(needAroundYou);	
		String needInString = gson.toJson(needAroundYou);
		
		out.print(needInString);
				
		}
	catch(JsonSyntaxException e) {
		out.print(e);;
	}
	}


	private void addpost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			
//			if(session.getAttribute("usernmae") != null) {
				
				String jsonFromAjax = request.getParameter("post");
				System.out.println(jsonFromAjax);
				
				Gson gson=new Gson();
				session=request.getSession();
				System.out.println(session.getAttribute("username").toString());
				
				PostBluePrint backingJavaObj=gson.fromJson(jsonFromAjax,PostBluePrint.class);
				String postingUser=session.getAttribute("username").toString();
				backingJavaObj.setUsername(postingUser);
				
				System.out.println(postingUser);
				System.out.println("Fetched the json data");
								
				// add the post to the database
				postDBUtil.addPost(backingJavaObj);
				
				System.out.println("added post");
				out.print(true);
//			}
//			else{
//				out.print(false);
//			}
//			
			
			
		      
			
			}
			catch(JsonSyntaxException e) {
				out.print(e);;
			}
		
	}


	private String findRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String route;
		
		if(request.getParameter("insert")!=null)
		{
			route="insert";
			return route;
		}
		else if(request.getParameter("signin")!=null)
		{
			route="signin";
			return route;
		}
		else if(request.getParameter("deleteRelation")!=null)
		{
			route="deleteRelation";
			return route;
		}
		else if(request.getParameter("approveThePost")!=null)
		{
			route="approveThePost";
			return route;
		}
		else if(request.getParameter("myProfileInfo")!=null)
		{
			route="myProfileInfo";
			return route;
		}
		else if(request.getParameter("OtpThPost")!=null)
		{
			route="OtpThPost";
			return route;
		}
		else if(request.getParameter("myOTP")!=null)
		{
			route="myOTP";
			return route;
		}
		else if(request.getParameter("myGiving")!=null)
		{
			route="myGiving";
			return route;
		}
		else if(request.getParameter("myTaking")!=null)
		{
			route="myTaking";
			return route;
		}
		else if(request.getParameter("myPostInfo")!=null)
		{
			route="myPostInfo";
			return route;
		}
		else if(request.getParameter("post")!=null)
		{
			route="post";
			return route;
		}
		else if(request.getParameter("needAroundYou")!=null)
		{
			route="needAroundYou";
			return route;
		}
		else if(request.getParameter("acceptThePost")!=null)
		{
			route="acceptThePost";
			return route;
		}
		else {
			return null;

		}
		
		
	}


	private void verifyUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			String jsonFromAjax = request.getParameter("signin");
			System.out.println(jsonFromAjax);
			
			Gson gson=new Gson();
			
			SigninBluePrint backingJavaObj=gson.fromJson(jsonFromAjax,SigninBluePrint.class);
			System.out.println("Fetched the json data");
							
			// add the student to the database
	    	boolean exist= sessionDBUtil.checkExist(backingJavaObj.getUsername(), backingJavaObj.getPassword());
			System.out.println(exist);
	    	if(exist) {
	    		sessionDBUtil.updateLatLong(backingJavaObj.getLatitude(),backingJavaObj.getLongitude(),backingJavaObj.getUsername());
	    	    session=request.getSession();  
			    session.setAttribute("username",backingJavaObj.getUsername());
			    System.out.println(session.getAttribute("username"));
			    out.print(true);
			    
			  
			    
	    	}
	    	else {
	    		out.print(false);
	    		}
	   
			}
			catch(JsonSyntaxException e) {
				System.out.print(e);
				out.print(e);;
			}	
		
		
	}


//	private void mainDashboardDispatcher(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		
//	}


	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		try {
		String jsonFromAjax = request.getParameter("insert");
		System.out.println(jsonFromAjax);
		
		Gson gson=new Gson();
		
		RegisterBluePrint backingJavaObj=gson.fromJson(jsonFromAjax,RegisterBluePrint.class);
		System.out.println("Fetched the json data");
						
		// add the student to the database
    	sessionDBUtil.addUser(backingJavaObj);
		String username=backingJavaObj.getUsername();
		
		System.out.println(username);
		out.print(true);
		
		 session=request.getSession();  
	     session.setAttribute("username",username);
	      
		
		}
		catch(JsonSyntaxException e) {
			out.print(e);;
		}
				
	}
	}

