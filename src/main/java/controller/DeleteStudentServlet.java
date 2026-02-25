package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rollNo = request.getParameter("roll_no");

        try {
        	Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("DELETE FROM students WHERE roll_no=?");
            pst.setString(1, rollNo);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                request.getSession().setAttribute("message", "Roll number " + rollNo + " deleted successfully.");
             
            } else {
                request.getSession().setAttribute("message", "Roll number " + rollNo + " is not yet added.");
            }

          
            response.sendRedirect("dashboard1.jsp");

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
