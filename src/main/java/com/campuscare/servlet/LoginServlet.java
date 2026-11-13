package com.campuscare.servlet;
import java.io.*;import javax.servlet.*;import javax.servlet.annotation.*;import javax.servlet.http.*;import com.campuscare.dao.StudentDAO;import com.campuscare.model.Student;
@WebServlet("/login") public class LoginServlet extends HttpServlet{
 protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{
  String u=q.getParameter("username"),p=q.getParameter("password");
  if("admin".equals(u)&&"admin".equals(p)){q.getSession().setAttribute("admin","admin");r.sendRedirect("admin-login.jsp");return;}
  Student s=new StudentDAO().login(u,p);
  if(s!=null){HttpSession x=q.getSession();x.setAttribute("studentId",s.getStudentId());x.setAttribute("studentName",s.getName());x.setAttribute("studentEmail",s.getEmail());r.sendRedirect("student-dashboard.jsp");}
  else r.sendRedirect("login.jsp?error=1");
 }}