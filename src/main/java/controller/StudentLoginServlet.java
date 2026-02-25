package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import javax.servlet.http.*;


@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rollNo = request.getParameter("roll_no");
        String password = request.getParameter("password");

        try {
        	Connection con = DBConnection.getConnection();

            // Check feedback access
            PreparedStatement accessStmt = con.prepareStatement("SELECT is_allowed FROM feedback_access WHERE id=1");
            ResultSet accessRs = accessStmt.executeQuery();
            if (accessRs.next() && !accessRs.getBoolean("is_allowed")) {
                response.getWriter().println("Feedback submission is currently closed.");
                return;
            }

            PreparedStatement pst = con.prepareStatement("SELECT * FROM students WHERE roll_no=? AND password=?");
            pst.setString(1, rollNo);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("feedback_submitted")) {
                    response.sendRedirect("already_submitted.jsp");
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("roll_no", rollNo);
                    response.sendRedirect("feedback.jsp");
                }
            } else {
            	
            	
            	 response.getWriter().println("Invalid Roll Number or Password");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
