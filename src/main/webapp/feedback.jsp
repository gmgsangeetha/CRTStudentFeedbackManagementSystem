<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CRT Classes Student Review Management System</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            background: #fff;
            margin: 50px auto;
            padding: 30px 40px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            border-radius: 10px;
        }
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
        }
        input[type="text"],
        input[type="email"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 18px;
            border-radius: 6px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }
        .rating-group {
            margin-bottom: 18px;
        }
        .rating-group label {
            font-weight: normal;
            margin-right: 15px;
        }
        .radio-row {
            display: flex;
            gap: 15px;
            margin-top: 8px;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>CRT Trainer Feedback Form</h2>
    <form action="/SubmitFeedbackServlet" method="post">

        <label for="email">Email ID:</label>
        <input type="email" name="email" id="email" required>

        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required>

<label for="rollno">Roll Number:</label>
<input type="text" name="rollno" id="rollno" value="<%= session.getAttribute("roll_no") %>" readonly style="background-color: #f0f0f0;">

        <label for="branch">Branch:</label>
        <input type="text" name="branch" id="branch" required>

        <label for="year">Year:</label>
        <select name="year" id="year" required>
            <option value="">--Select--</option>
            <option value="1st Year">1st Year</option>
            <option value="2nd Year">2nd Year</option>
            <option value="3rd Year">3rd Year</option>
            <option value="4th Year">4th Year</option>
        </select>

        <label for="trainer">Trainer Name:</label>
        <input type="text" name="trainer" id="trainer" required>

        <div class="rating-group">
            <label>Trainer Rating (out of 5):</label>
            <div class="radio-row">
                <label><input type="radio" name="trainerRating" value="5" required> 5</label>
                <label><input type="radio" name="trainerRating" value="4"> 4</label>
                <label><input type="radio" name="trainerRating" value="3"> 3</label>
                <label><input type="radio" name="trainerRating" value="2"> 2</label>
                <label><input type="radio" name="trainerRating" value="1"> 1</label>
            </div>
        </div>

        <div class="rating-group">
            <label>Rating for Training (out of 5):</label>
            <div class="radio-row">
                <label><input type="radio" name="trainingRating" value="5" required> 5</label>
                <label><input type="radio" name="trainingRating" value="4"> 4</label>
                <label><input type="radio" name="trainingRating" value="3"> 3</label>
                <label><input type="radio" name="trainingRating" value="2"> 2</label>
                <label><input type="radio" name="trainingRating" value="1"> 1</label>
            </div>
        </div>

        <label for="review">Review about Trainer:</label>
        <textarea name="review" id="review" rows="3" required></textarea>

        <label for="suggestions">Suggestions on Trainer Performance:</label>
        <textarea name="suggestions" id="suggestions" rows="3"></textarea>

        <label for="comments">Other Comments (if any):</label>
        <textarea name="comments" id="comments" rows="3"></textarea>

        <input type="submit" value="Submit Feedback">

    </form>
</div>

</body>
</html>
