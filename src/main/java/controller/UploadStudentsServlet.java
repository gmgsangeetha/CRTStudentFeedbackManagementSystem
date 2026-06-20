package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;

@WebServlet("/UploadStudentsServlet")
@MultipartConfig
public class UploadStudentsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("file");

        int count = 0;
        int skipped = 0;

        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(filePart.getInputStream()))
        ) {

            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students (roll_no, password) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            String line;
            boolean first = true;

            while ((line = br.readLine()) != null) {

                // skip header row
                if (first) {
                    first = false;
                    continue;
                }

                String[] data = line.split("[,\t]");

                if (data.length < 2) continue;

                String roll = data[0].trim();
                String pass = data[1].trim();

                try {
                    ps.setString(1, roll);
                    ps.setString(2, pass);
                    ps.executeUpdate();
                    count++;

                } catch (SQLIntegrityConstraintViolationException e) {
                    skipped++; // duplicate roll_no
                }
            }

            ps.close();
            con.close();

            request.getSession().setAttribute(
                "message",
                "Bulk Upload Completed! Added: " + count + " students, Skipped(duplicates found): " + skipped
            );

            response.sendRedirect("dashboard1.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Upload failed!");
            response.sendRedirect("dashboard1.jsp");
        }
    }
}
