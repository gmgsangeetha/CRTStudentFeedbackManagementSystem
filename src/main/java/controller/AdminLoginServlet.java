package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection con = DBConnection.getConnection()) { // ✅ Replaced manual connection with DBConnection
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM admin WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("adminEmail", email);
               
                response.sendRedirect("dashboard1.jsp");

            

               

            } else {
                response.getWriter().println("Invalid admin credentials");
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred during login.");
        }
    }
}
