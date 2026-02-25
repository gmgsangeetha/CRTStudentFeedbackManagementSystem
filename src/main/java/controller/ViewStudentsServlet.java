package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.*;


@WebServlet("/ViewStudentsServlet")
public class ViewStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
        	Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            out.println("<h2 style='text-align:center;'>Registered Students</h2>");
            int count = 0;

            while (rs.next()) {
                count++;
                out.println("<div style='border:1px solid #ccc; padding:10px; margin:10px;'>");
                out.println("<strong>Roll No:</strong> " + rs.getString("roll_no") + "<br>");
                out.println("<strong>Password:</strong> " + rs.getString("password") + "<br>");
                out.println("<strong>Feedback Submitted:</strong> " + (rs.getInt("feedback_submitted") == 1 ? "Yes" : "No") + "<br>");
                out.println("</div>");
            }

            if (count == 0) {
                out.println("<p style='text-align:center; color:red;'>No students found.</p>");
            } else {
                out.println("<p style='text-align:center;'><strong>Total Students:</strong> " + count + "</p>");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}

