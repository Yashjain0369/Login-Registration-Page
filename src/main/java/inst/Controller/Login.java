package inst.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.exceptions.RSAException;

import inst.DataBaseConnection.DB_Con;
import inst.Model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginform")
public class Login extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
	PrintWriter out = resp.getWriter();
	resp.setContentType("text/html");
	
	String myemail1 = req.getParameter("email1");
	String mypass1 = req.getParameter("password1");
	
	try {
		Connection con = DB_Con.getConnection();
		
		String select_query = "SELECT  * FROM register WHERE email = ? AND  password = ?";
 		
		PreparedStatement ps  = con.prepareStatement(select_query);
		
		ps.setString(1, myemail1);
		ps.setString(2, mypass1);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			out.println("<h3 style = 'color : green' > Login Successfully </h3>");
			
			User user = new User();
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setCity(rs.getString("city"));
			
			
			HttpSession session = req.getSession();
		session.setAttribute("session_user", user);
			
			RequestDispatcher  rd = req.getRequestDispatcher("/profile.jsp");
			rd.forward(req, resp);
 		
		}
		else {
			
			out.println("<h3 style = 'color : red'  Login Failed");
			
			RequestDispatcher  rd = req.getRequestDispatcher("/login.html");
			rd.include(req, resp);
		}
		
		
	}
	
	catch(Exception e){
		
		e.printStackTrace();
	}
	
}
}
