package com.campuscare.servlet;
import java.io.*;import javax.servlet.*;import javax.servlet.annotation.*;import javax.servlet.http.*;import com.campuscare.dao.ComplaintDAO;
@WebServlet("/complaint") public class ComplaintServlet extends HttpServlet{
 protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{
  HttpSession s=q.getSession(false);if(s==null||s.getAttribute("studentId")==null){r.sendRedirect("login.jsp");return;}
  int id=(Integer)s.getAttribute("studentId");boolean ok=new ComplaintDAO().create(id,q.getParameter("category"),q.getParameter("title"),q.getParameter("description"),q.getParameter("location"),q.getParameter("priority"));
  r.sendRedirect(ok?"student-dashboard.jsp?success=1":"raise-complaint.jsp?error=1");
 }}