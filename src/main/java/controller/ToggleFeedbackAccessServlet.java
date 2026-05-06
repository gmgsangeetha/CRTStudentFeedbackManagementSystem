package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ToggleFeedbackAccessServlet")
public class ToggleFeedbackAccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean allow = "Allow".equalsIgnoreCase(action);
        try {
        	Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("UPDATE feedback_access SET is_allowed = ? WHERE id = 1");
            pst.setBoolean(1, allow);
            pst.executeUpdate();
            pst.close();
            con.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


