package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.sql.Connection;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rollNo = request.getParameter("roll_no");
        String password = request.getParameter("password");

        try {
            
            Connection con = DBConnection.getConnection();

            PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO students (roll_no, password) VALUES (?, ?)");
            pst.setString(1, rollNo);
            pst.setString(2, password);
            pst.executeUpdate();

            request.getSession().setAttribute("message", "Roll number " + rollNo + " added successfully.");
            
            response.sendRedirect("dashboard1.jsp");

            




        } catch (SQLIntegrityConstraintViolationException dup) {
            // If roll_no is a primary key and already exists
            request.getSession().setAttribute("message", "Roll number " + rollNo + " already exists.");
           
            response.sendRedirect("dashboard1.jsp");

            


        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Something went wrong: " + e.getMessage());
            response.sendRedirect("dashboard1.jsp");

            
         

        }
    }
}
