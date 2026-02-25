# CRT Student Feedback Management System

A **web-based platform** designed to efficiently collect and manage student feedback. Only registered students can submit feedback **once**, while the admin has full control over access and visibility.

---

## Features

### Admin
- Secure login with email and password
- Add, update, and delete student records
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
- **Development IDE:** Eclipse  

---

## Live Application

**Admin URL:** [http://studentfeedback.alwaysdata.net/login.jsp](http://studentfeedback.alwaysdata.net/login.jsp)  
> Requires valid admin credentials. Contact the project owner for access.

**Student URL:** [http://studentfeedback.alwaysdata.net/](http://studentfeedback.alwaysdata.net/)
> Requires registered student credentials. Contact the project owner for access.

---

## Project Structure
```
CRTStudentFeedbackManagementSystem/
│
├─ src/main/java/controller/
│ ├─ AddStudentServlet.java
│ ├─ AdminLoginServlet.java
│ ├─ ChangeAdminPasswordServlet.java
│ ├─ DBConnection.java
│ ├─ DeleteStudentServlet.java
│ ├─ LogoutServlet.java
│ ├─ StudentLoginServlet.java
│ ├─ SubmitFeedbackServlet.java
│ ├─ ToggleFeedbackAccessServlet.java
│ ├─ ViewFeedbackServlet.java
│ ├─ ViewStudentsServlet.java
│ └─ package-info.java
│
├─ src/main/webapp/
│ ├─ META-INF/MANIFEST.MF
│ ├─ WEB-INF/lib/javax.servlet-api-4.0.1.jar
│ ├─ WEB-INF/lib/mysql-connector-j-8.0.33.jar
│ ├─ already_submitted.jsp
│ ├─ change-password.jsp
│ ├─ dashboard1.jsp
│ ├─ feedback.jsp
│ ├─ index.jsp
│ ├─ login.jsp
│ └─ thank_you.jsp
│
├─ .gitignore
└─ README.md
```

---

## How It Works

**Admin Workflow:**
1. Log in with admin credentials.
2. Manage students and feedback access.
3. View and analyze submitted feedback.

**Student Workflow:**
1. Log in with roll number and password.
2. Fill out the feedback form (auto-filled roll number).
3. Submit feedback once.

---

## Benefits
- Prevents duplicate feedback submissions
- Real-time admin feedback management
- Auto-fills student info(roll number) in the feedback form for accuracy
- Reliable feedback collection

---

## GitHub Repository
[CRT Student Feedback Management System](https://github.com/gmgsangeetha/CRTStudentFeedbackManagementSystem)
