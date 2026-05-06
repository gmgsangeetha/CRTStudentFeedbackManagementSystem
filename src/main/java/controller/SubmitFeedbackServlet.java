package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/SubmitFeedbackServlet")
public class SubmitFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("roll_no") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String rollNo = (String) session.getAttribute("roll_no");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String branch = request.getParameter("branch");
        String year = request.getParameter("year");
        String trainerName = request.getParameter("trainer");
        String trainerRatingStr = request.getParameter("trainerRating");
        String trainingRatingStr = request.getParameter("trainingRating");
        String review = request.getParameter("review");
        String suggestions = request.getParameter("suggestions");
        String comments = request.getParameter("comments");
        int trainerRating = 0;
        int trainingRating = 0;
        try {
            trainerRating = Integer.parseInt(trainerRatingStr);
            trainingRating = Integer.parseInt(trainingRatingStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid rating value.");
            return;
        }
        try {
        	Connection con = DBConnection.getConnection();
            // Check if already submitted
            String checkSQL = "SELECT feedback_submitted FROM students WHERE roll_no=?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkSQL)) {
                checkStmt.setString(1, rollNo);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getBoolean("feedback_submitted")) {
                    response.sendRedirect("already_submitted.jsp");
                    return;
                }
            }
            // Insert feedback
            String insertSQL = "INSERT INTO feedbacks (email, name, roll_no, branch, year, trainer_name, trainer_rating, training_rating, review, suggestions, comments) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = con.prepareStatement(insertSQL)) {
                insertStmt.setString(1, email);
                insertStmt.setString(2, name);
                insertStmt.setString(3, rollNo);
                insertStmt.setString(4, branch);
                insertStmt.setString(5, year);
                insertStmt.setString(6, trainerName);
                insertStmt.setInt(7, trainerRating);
                insertStmt.setInt(8, trainingRating);
                insertStmt.setString(9, review);
                insertStmt.setString(10, suggestions);
                insertStmt.setString(11, comments);
                insertStmt.executeUpdate();
            }
            // Update feedback status
            String updateSQL = "UPDATE students SET feedback_submitted=TRUE WHERE roll_no=?";
            try (PreparedStatement updateStmt = con.prepareStatement(updateSQL)) {
                updateStmt.setString(1, rollNo);
                updateStmt.executeUpdate();
            }
            response.sendRedirect("thank_you.jsp");
            } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Something went wrong. Please try again later.");
        }
    }
}
