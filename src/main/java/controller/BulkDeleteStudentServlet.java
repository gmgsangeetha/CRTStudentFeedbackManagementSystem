package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/BulkDeleteStudentServlet")
public class BulkDeleteStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String data = request.getParameter("roll_numbers");
        if (data == null || data.trim().isEmpty()) {
            request.getSession().setAttribute(
                "message",
                "Please enter at least one roll number to delete."
            );
            response.sendRedirect("dashboard1.jsp");
            return;
        }
        String[] rolls = data.split("[,\\r\\n\\t]+");

        int deleted = 0;
        StringBuilder notFound = new StringBuilder();

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement pst =
                con.prepareStatement("DELETE FROM students WHERE roll_no=?");

            for (String roll : rolls) {

                roll = roll.trim();
                if (roll.isEmpty()) continue;

                pst.setString(1, roll);

                int rows = pst.executeUpdate();

                if (rows > 0) {
                    deleted++;
                } else {
                    notFound.append(roll).append(" ");
                }
            }

            pst.close();
            con.close();

            String msg;

            if (deleted > 0 && notFound.length() == 0) {

                if (deleted == 1) {
                    msg = "1 student deleted successfully.";
                } else {
                    msg = deleted + " students deleted successfully.";
                }

            }
            else if (deleted > 0) {

                if (deleted == 1) {
                    msg = "1 student deleted successfully. Not found: "
                            + notFound.toString();
                } else {
                    msg = deleted + " students deleted successfully. Not found: "
                            + notFound.toString();
                }

            }
            else {
                msg = "No students were deleted. Roll numbers not found: "
                        + notFound.toString();
            }
            request.getSession().setAttribute("message", msg);
            response.sendRedirect("dashboard1.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
