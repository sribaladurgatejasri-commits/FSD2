package com.campuscare.servlet;
import java.io.*;import javax.servlet.*;import javax.servlet.annotation.*;import javax.servlet.http.*;import com.campuscare.dao.ComplaintDAO;
@WebServlet("/updateComplaint") public class UpdateComplaintServlet extends HttpServlet{
 protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{
  HttpSession s=q.getSession(false);if(s==null||s.getAttribute("adminRole")==null){r.sendRedirect("admin-login.jsp");return;}
  new ComplaintDAO().update(Integer.parseInt(q.getParameter("complaintId")),q.getParameter("status"),q.getParameter("remarks"));r.sendRedirect("admin.jsp");
 }}