CAMPUSCARE - FULL STACK JAVA SERVLET PROJECT

Server: Tomcat 9
Java: 17
Database: Oracle XE
Frontend: HTML/CSS/JSP
Backend: Java Servlet + JDBC

Oracle:
username grievance
password grievance123
URL jdbc:oracle:thin:@localhost:1521:xe

Application admin:
username admin
password admin

Complaint admins:
warden@campuscare.com / 1234
canteen@campuscare.com / 1234
classadmin@campuscare.com / 1234
lab@campuscare.com / 1234
transport@campuscare.com / 1234
library@campuscare.com / 1234
maintenance@campuscare.com / 1234
security@campuscare.com / 1234

IMPORTANT:
1. Run database.sql as GRIEVANCE.
2. Import the folder as an Existing Maven Project in Eclipse.
3. Maven > Update Project.
4. Add Tomcat 9.
5. Run on Server.
6. Open http://localhost:8080/CampusCare/

Privacy:
The complaint admin dashboard never selects or displays student identity columns.
The database still keeps student_id internally so the student can track their own complaint.
