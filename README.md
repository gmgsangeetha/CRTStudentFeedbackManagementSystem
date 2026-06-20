# CRT Student Feedback Management System

A **web-based platform** designed to efficiently collect and manage student feedback. Only registered students can submit feedback **once**, while the admin has full control over access and visibility.

---

## Project Motivation

There are many feedback management systems available, including proprietary and free trial tools. However, in our college, feedback collection was done using Google Forms, which had limitations in customization and management.

During discussions with our mentor, we identified the need for a dedicated system tailored to institutional requirements. As a student, I aimed to understand and implement a complete web application involving backend, database, and frontend integration.

This project helped me gain practical experience in full-stack web development, including request handling, session management, and database integration using a servlet-based architecture.

---

## Features

### Admin
- Secure login with email and password
- Add and delete bulk student records at a time
- Enable or disable feedback form access
- View feedback with filters (trainer, rating, roll number, submission date)
- Change admin password securely

### Student
- Login with roll number and password set by the admin
- Auto-filled feedback form with roll number
- Submit feedback **once only** to prevent duplicates

---

## Technology Stack
- **Frontend:** HTML, CSS, JavaScript  
- **Backend:** Java Servlets, JSP  
- **Database:** MySQL  
- **Hosting:** AlwaysData  
- **Development IDE:** Eclipse IDE for Enterprise Java and Web Developers

---

## Live Application

**Admin URL:** [http://studentfeedback.alwaysdata.net/login.jsp](http://studentfeedback.alwaysdata.net/login.jsp)  
> Requires valid admin credentials. Contact the project owner for access.

**Student URL:** [http://studentfeedback.alwaysdata.net/](http://studentfeedback.alwaysdata.net/)
> Requires registered student credentials. Contact the project owner for access.

---

## Project Structure

```text
CRTStudentFeedbackManagementSystem/
│
├─ src/main/java/controller/
│ ├─ AddStudentServlet.java
│ ├─ AdminLoginServlet.java
│ ├─ BulkDeleteStudentServlet.java
│ ├─ ChangeAdminPasswordServlet.java
│ ├─ DBConnection.java
│ ├─ DeleteStudentServlet.java
│ ├─ LogoutServlet.java
│ ├─ StudentLoginServlet.java
│ ├─ SubmitFeedbackServlet.java
│ ├─ ToggleFeedbackAccessServlet.java
│ ├─ UploadStudentsServlet.java
│ ├─ ViewFeedbackServlet.java
│ ├─ ViewStudentsServlet.java
│ └─ package-info.java
│
└─ src/main/webapp/
  ├─ META-INF/MANIFEST.MF
  ├─ WEB-INF/lib/javax.servlet-api-4.0.1.jar
  ├─ WEB-INF/lib/mysql-connector-j-8.0.33.jar
  ├─ already_submitted.jsp
  ├─ bulk-upload.jsp
  ├─ change-password.jsp
  ├─ dashboard1.jsp
  ├─ feedback.jsp
  ├─ index.jsp
  ├─ login.jsp
  └─ thank_you.jsp
```

---

## How It Works

### Admin Workflow:

1. Log in with admin credentials.
2. Add a single student account by entering the Roll Number and Password.
3. Bulk upload multiple student accounts using a CSV file.

   * Create an Excel sheet with:

     ```text
     RollNo,Password
     23NM1A0513,pass123
     23NM1A0554,pass456
     ```
   * In Excel, click **File → Save As → CSV (Comma delimited) (*.csv)**.
   * Upload the generated CSV file through the **Bulk Upload Students** option.
4. Delete student accounts using the Delete Students option:

   * Enter a single roll number to delete one student.
   * Enter multiple roll numbers (one per line) to delete multiple students at once.
5. Enable or disable feedback submission using a toggle switch. When feedback access is disabled, students cannot submit feedback and are shown a message    indicating that the feedback session is currently closed.
6. View registered students.
7. Filter, view, and analyze submitted feedback.
8. Change admin password and manage system access.

### Student Workflow:

1. Log in with roll number and password.
2. Fill out the feedback form (auto-filled roll number).
3. Submit feedback once.
4. Receive a confirmation after successful submission.

---

## Benefits
- Prevents duplicate feedback submissions
- Reliable feedback collection
- Secure role-based access (Admin & Student)
- Auto-fills student info(roll number) in the feedback form for accuracy
- Once the feedback is submitted for today,on the next day the student will be able to fill the feedback form again, as the automatic reset feature is added.
- Real-time feedback control by admin  
- Clean MVC-style servlet architecture  
- Hosted and accessible online
- Reduces manual effort in feedback collection
- Ensures controlled and authenticated access 

---

## Future Scope

- Adding data visualization dashboards (bar charts, pie charts) for feedback analysis  
- Exporting feedback data into CSV/Excel format for reporting and analysis  
- Adding analytics-based insights for trainers and training quality evaluation  

---

## GitHub Repository
[CRT Student Feedback Management System](https://github.com/gmgsangeetha/CRTStudentFeedbackManagementSystem)
