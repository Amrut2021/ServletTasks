package demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/forget")
public class ForgetPassServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String newpassword=req.getParameter("new-password");
		String confirmpassword=req.getParameter("confirm-password");
		
		if (newpassword== null || confirmpassword == null || !newpassword.equals(confirmpassword))
		{
            resp.getWriter().write("Passwords do not match!");
            return;
		}
		if(newpassword.isEmpty())
		{
			 resp.getWriter().write("Password cannot be empty!");
	            return;
		}
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSl=false","root","Amruta@2021");
			
			PreparedStatement ps=c.prepareStatement("update registerdemo set password=? where email=?");
			
			ps.setString(1, newpassword);
			
			ps.setString(2, email);
			  int rowsUpdated = ps.executeUpdate();

	            // Check if any row was updated
	            if (rowsUpdated > 0) {
	                resp.getWriter().write("Password updated successfully!");
	            } else {
	                resp.getWriter().write("Email not found.");
	            }
			
			c.close();
				System.out.println("password updated");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	}

