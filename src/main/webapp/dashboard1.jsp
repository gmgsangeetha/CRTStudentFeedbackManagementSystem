<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.sql.Connection, java.sql.Statement, java.sql.ResultSet" %>
<%@ page import="controller.DBConnection" %>

<%
    String adminEmail = (String) session.getAttribute("adminEmail");
    if (adminEmail == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        margin: 0;
    }

    .dashboard-container {
        width: 100%;
        max-width: 600px;
        padding: 30px;
        background-color: #fff;
        border-radius: 12px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.1);
    }

    .top-links {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;
    }

    a {
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
    }

    a:hover {
        text-decoration: underline;
    }

    form {
        margin-bottom: 30px;
    }

    h3 {
        margin-bottom: 15px;
        color: #333;
    }

    label {
        font-weight: bold;
        display: block;
        margin-bottom: 6px;
    }

    input[type="text"],
    input[type="date"],
    select {
        width: 100%;
        padding: 10px;
        margin-bottom: 16px;
        border: 1px solid #ccc;
        border-radius: 6px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #28a745;
        color: white;
        padding: 12px;
        width: 100%;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-size: 16px;
    }

    input[type="submit"]:hover {
        background-color: #218838;
    }

    .toggle-switch {
        position: relative;
        display: inline-block;
        width: 60px;
        height: 30px;
        margin-top: 10px;
    }

    .toggle-switch input {
        display: none;
    }

    .slider {
        position: absolute;
        cursor: pointer;
        background-color: #ccc;
        border-radius: 34px;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        transition: 0.4s;
    }

    .slider:before {
        content: "";
        position: absolute;
        height: 24px;
        width: 24px;
        left: 4px;
        bottom: 3px;
        background-color: white;
        border-radius: 50%;
        transition: 0.4s;
    }

    input:checked + .slider {
        background-color: #28a745;
    }

    input:checked + .slider:before {
        transform: translateX(26px);
    }

    .status-label {
        margin-left: 12px;
        font-weight: bold;
        vertical-align: middle;
    }
</style>
</head>
<body>

<div class="dashboard-container">

<%  // Message block
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
    <div style="text-align: center; margin: 10px 0 20px;">
        <span style="color: green; font-size: 18px; font-weight: bold; background-color: #e0ffe0; padding: 10px 20px; border-radius: 8px; display: inline-block;">
            <%= message %>
        </span>
    </div>
<%
        session.removeAttribute("message");
    }
%>

<!-- Top Navbar Links -->
<div class="top-links">
    <a href="change-password.jsp">Change Password</a>
    <a href="<%= request.getContextPath() %>/LogoutServlet">Logout</a>
</div>

<!-- Add Student Form -->
<form action="<%=request.getContextPath()%>/AddStudentServlet" method="post">
    <h3>Add Student</h3>
    <label for="add_roll_no">Roll No:</label>
    <input type="text" id="add_roll_no" name="roll_no" required>

    <label for="add_password">Password:</label>
    <input type="text" id="add_password" name="password" required>

    <input type="submit" value="Add Student">
</form>

<!-- Delete Student Form -->
<form action="<%=request.getContextPath()%>/DeleteStudentServlet" method="post">
    <h3>Delete Student</h3>
    <label for="delete_roll_no">Roll No:</label>
    <input type="text" id="delete_roll_no" name="roll_no" required>

    <input type="submit" value="Delete Student">
</form>

<!-- View Students Button -->
<form action="<%=request.getContextPath()%>/ViewStudentsServlet" method="get">
    <input type="submit" value="View Students" style="background-color:#007bff; color:white;">
</form>

<!-- View Feedback with Filters -->
<form action="<%=request.getContextPath()%>/ViewFeedbackServlet" method="get">
    <h3>Filter Feedback</h3>

    <label for="trainer_name">Trainer Name:</label>
    <input type="text" id="trainer_name" name="trainer_name">

    <label for="trainer_rating">Trainer Rating:</label>
    <select id="trainer_rating" name="trainer_rating">
        <option value="">All</option>
        <option>1</option><option>2</option><option>3</option>
        <option>4</option><option>5</option>
    </select>

    <label for="training_rating">Training Rating:</label>
    <select id="training_rating" name="training_rating">
        <option value="">All</option>
        <option>1</option><option>2</option><option>3</option>
        <option>4</option><option>5</option>
    </select>

    <label for="created_at">Date:</label>
    <input type="date" id="created_at" name="created_at">

    <label for="filter_roll_no">Student Roll Number:</label>
    <input type="text" id="filter_roll_no" name="roll_no">

    <input type="submit" value="Filter">
</form>

<%
    // Fetch is_allowed
    int status = 0;
    try {
        
    	Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT is_allowed FROM feedback_access WHERE id=1");
        if (rs.next()) {
            status = rs.getInt("is_allowed");
        }
        rs.close(); st.close(); con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<h3>Toggle Feedback Access</h3>
<label class="toggle-switch">
    <input type="checkbox" id="toggle" onchange="updateAccess()" <%= (status == 1 ? "checked" : "") %>>
    <span class="slider"></span>
</label>
<span class="status-label" id="statusText"><%= (status == 1 ? "Allow" : "Prohibit") %></span>

</div>

<script>
function updateAccess() {
    const checkbox = document.getElementById('toggle');
    const newStatus = checkbox.checked ? "Allow" : "Prohibit";
    document.getElementById('statusText').textContent = newStatus;

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "<%=request.getContextPath()%>/ToggleFeedbackAccessServlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("action=" + encodeURIComponent(newStatus));
}
</script>

</body>
</html>
