package demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?useSSl=false", "root",
					"Amruta@2021");
			PreparedStatement ps = con.prepareStatement(
					"insert into register(name,lname,email,password,city,state,country)values(?,?,?,?,?,?,?);");
			ps.setString(1, name);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, pass);
			ps.setString(5, city);
			ps.setString(6, state);
			ps.setString(7, country);
			ps.executeUpdate();
			System.out.println("data inserted");
			con.close();

			PrintWriter out = resp.getWriter();
			out.print("data inserted");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
