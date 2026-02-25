package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.sql.Date;

@WebServlet("/ViewFeedbackServlet")
public class ViewFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String trainer = request.getParameter("trainer_name");
        String trainerRating = request.getParameter("trainer_rating");
        String trainingRating = request.getParameter("training_rating");
        String date = request.getParameter("created_at");
        String rollNo = request.getParameter("roll_no");
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
        	Connection con = DBConnection.getConnection();

            StringBuilder sql = new StringBuilder("SELECT * FROM feedbacks WHERE 1=1");
            List<Object> params = new ArrayList<>();
            
            if (rollNo != null && !rollNo.isEmpty()) {
                sql.append(" AND roll_no LIKE ?");
                params.add("%" + rollNo + "%");
            }


            if (trainer != null && !trainer.trim().isEmpty()) {
                sql.append(" AND trainer_name LIKE ?");
                params.add("%" + trainer.trim() + "%");
            }

            if (trainerRating != null && !trainerRating.trim().isEmpty() && !trainerRating.equals("0")) {
                sql.append(" AND trainer_rating = ?");
                params.add(Integer.parseInt(trainerRating.trim()));
            }

            if (trainingRating != null && !trainingRating.trim().isEmpty() && !trainingRating.equals("0")) {
                sql.append(" AND training_rating = ?");
                params.add(Integer.parseInt(trainingRating.trim()));
            }

            if (date != null && !date.trim().isEmpty()) {
                sql.append(" AND DATE(created_at) = ?");
                params.add(Date.valueOf(date.trim()));
            }

            PreparedStatement pst = con.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); i++) {
                pst.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pst.executeQuery();

            out.println("<div style='display: flex; justify-content: space-between; align-items: center;'>");
            out.println("<h2 style='margin: 0;'>Feedback Results</h2>");
            out.println("<div id='feedback-count' style='font-size: 18px; color: #007bff; font-weight: bold;'>Total Feedbacks: 0</div>");
            out.println("</div><hr>");


            boolean hasResults = false;
            int count = 0;

            while (rs.next()) {
                hasResults = true;
                count++;
                out.println("<div style='border:1px solid #ccc; padding:10px; margin-bottom:10px;'>");
                out.println("<strong>Name:</strong> " + rs.getString("name") + "<br>");
                out.println("<strong>Email:</strong> " + rs.getString("email") + "<br>");
                out.println("<strong>Roll No:</strong> " + rs.getString("roll_no") + "<br>");
                out.println("<strong>Branch:</strong> " + rs.getString("branch") + "<br>");
                out.println("<strong>Year:</strong> " + rs.getString("year") + "<br>");
                out.println("<strong>Trainer:</strong> " + rs.getString("trainer_name") + "<br>");
                out.println("<strong>Trainer Rating:</strong> " + rs.getInt("trainer_rating") + "<br>");
                out.println("<strong>Training Rating:</strong> " + rs.getInt("training_rating") + "<br>");
                out.println("<strong>Review:</strong> " + rs.getString("review") + "<br>");
                out.println("<strong>Suggestions:</strong> " + rs.getString("suggestions") + "<br>");
                out.println("<strong>Comments:</strong> " + rs.getString("comments") + "<br>");
                out.println("<strong>Submitted At:</strong> " + rs.getTimestamp("created_at") + "<br>");
                out.println("</div>");
            }

            if (!hasResults) {
                out.println("<p style='color:red;'>No feedback found for the applied filters.</p>");
            }
            else {
                out.println("<script>");
                out.println("document.getElementById('feedback-count').innerText = 'Total Feedbacks: " + count + "';");
                out.println("</script>");
            }


            rs.close();
            pst.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
