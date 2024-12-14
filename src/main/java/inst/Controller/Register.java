package inst.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import inst.DataBaseConnection.DB_Con;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regform")
public class Register extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out  = resp.getWriter();	
		resp.setContentType("text/html");
		
		 String myname = req.getParameter("name1");
		 String myemail = req.getParameter("email1");
		 String mypassword = req.getParameter("password1");
		 String mycity = req.getParameter("city1");
		 
		 
		 try {
			 Connection con = DB_Con.getConnection();
			 
			 
			 String insert_query = "INSERT INTO register VALUES (?,?,?,?)";
			 
			 PreparedStatement  ps = con.prepareStatement(insert_query);
			 ps.setString(1, myname);
			 ps.setString(2, myemail);
			 ps.setString(3, mypassword );
			 ps.setString(4, mycity);
			 
			 int count = ps.executeUpdate();
			 
			 if(count>0) {
				 
				 out.println("<h3 style = 'color:green' > Registeration  Completed  </h3>");
				 
				 RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				 rd.include(req, resp);
			 }
			 else {
			out.println("<h3  style = 'color:red'>Registeration  Not Completed </h3>");
			
			RequestDispatcher rd  = req.getRequestDispatcher("/regform.html");
			rd.include(req, resp);
			 }
		 }
		 catch (Exception e) {
		
		e.printStackTrace();
		}
	
	}

}