package com.unknown;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.util.DbConnection;

@WebServlet("/expregister")
public class expregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
	
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();            
        String Addeddate=dateFormat.format(date);
		
		PrintWriter out=res.getWriter();
		
	
		String fname=req.getParameter("fname");
		
		String lname=req.getParameter("lname");
		String dob=req.getParameter("dob");
		
		String gender=req.getParameter("gender");
		
		String address=req.getParameter("address");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		//String college=req.getParameter("college");
		//String university=req.getParameter("uniersity");
		/*String studyingfields=req.getParameter("studyingfields");*/
		//String designation=req.getParameter("designation");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		//String location=req.getParameter("location");
		//String lat=req.getParameter("lat");
		//String lon=req.getParameter("lon");
		//String language=req.getParameter("language");
		String expert=req.getParameter("expert");
		
		
		
		try 
		{
			Connection con = DbConnection.getConnection();
			PreparedStatement ps;

			 
			
			String sql = "select * from expert where email='"+email+"' or username='"+username+"'";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			if(!rs.next()){
			
			ps=con.prepareStatement("insert into expert (fname,lname,dob,gender,email,mobile,address,username,password,regDate,expert) values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,fname);
			ps.setString(2,lname);
			ps.setString(3, dob);
			ps.setString(4, gender);
			ps.setString(5, email);
			ps.setString(6, mobile);
			ps.setString(7, address);
			ps.setString(8, username);
			ps.setString(9, password);
			ps.setString(10, Addeddate); 
			ps.setString(11, expert);
			//ps.setString(13, language);
			//ps.setString(11, group);
			
			
			//ps.setString(12,file.getName());
		
			//System.out.println("file name "+file.getName());
			int result=ps.executeUpdate();
			 
			if (result > 0)
			{
				System.out.println("User Registration successfull");
				
				res.sendRedirect("expert.jsp?done");
				
				
			}
			else
			{
				System.out.println("Registration failed");
				res.sendRedirect("expregister.jsp");
			}
			}else{
				System.out.println("Email exists");
				res.sendRedirect("expregister.jsp?exists");
				
			}
		  
		    
		}
		
		catch(Exception e1)
		{
			e1.printStackTrace();
		}

}
	
		

}
