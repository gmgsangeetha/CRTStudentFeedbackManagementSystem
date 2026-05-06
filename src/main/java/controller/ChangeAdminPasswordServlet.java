package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ChangeAdminPasswordServlet")
public class ChangeAdminPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET not supported");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String current = request.getParameter("currentPassword");
        String newPass = request.getParameter("newPassword");
        String confirm = request.getParameter("confirmPassword");
        if (!newPass.equals(confirm)) {
            request.setAttribute("message", "New password and confirm password do not match.");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }
        String adminEmail = (String) request.getSession().getAttribute("adminEmail");
        if (adminEmail == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
            try (PreparedStatement check = con.prepareStatement(sql)) {
                check.setString(1, adminEmail);
                check.setString(2, current);
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    String updateSQL = "UPDATE admin SET password = ? WHERE email = ?";
                    try (PreparedStatement update = con.prepareStatement(updateSQL)) {
                        update.setString(1, newPass);
                        update.setString(2, adminEmail);
                        update.executeUpdate();
                    }
                    request.getSession().invalidate(); 
                    response.sendRedirect("login.jsp?message=Password changed successfully. Please log in again.");
                    return;
                } else {
                    request.setAttribute("message", "Incorrect current password.");
                    request.getRequestDispatcher("changepassword.jsp").forward(request, response);   
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        }
    }
}
