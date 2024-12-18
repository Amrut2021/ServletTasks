package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/loginform")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("username");
		String pass=req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSl=false","root","Amruta@2021");
			PreparedStatement ps = c.prepareStatement("select * from registerdemo where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("<h1>'login Successfull'</h1>");
			PrintWriter out=resp.getWriter();
			out.println("<html><body>");
	        out.println("<script type='text/javascript'>");
	        out.println("alert('login Successfull!');");
	        out.println("window.location.href = 'HomePage.html';"); // Optional: Redirect to another page after the alert
	        out.println("</script>");
	        out.println("</body></html>");
//			out.print("<h1 style=color:green >login Successfully</h1>");
			}
			else {
				System.out.println("User & Password Did'nt Match");
				PrintWriter out=resp.getWriter();
				out.print("User & Password Did'nt Match");
			}
			c.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
