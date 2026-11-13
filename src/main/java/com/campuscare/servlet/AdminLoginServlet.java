package com.campuscare.servlet;
import java.io.*;import javax.servlet.*;import javax.servlet.annotation.*;import javax.servlet.http.*;
@WebServlet("/adminLogin") public class AdminLoginServlet extends HttpServlet{
 protected void doPost(HttpServletRequest q,HttpServletResponse r)throws IOException{
  String e=q.getParameter("email"),p=q.getParameter("password"),role=null;
  if("warden@campuscare.com".equals(e)&&"1234".equals(p))role="HOSTEL";
  else if("canteen@campuscare.com".equals(e)&&"1234".equals(p))role="CANTEEN";
  else if("classadmin@campuscare.com".equals(e)&&"1234".equals(p))role="CLASSROOM";
  else if("lab@campuscare.com".equals(e)&&"1234".equals(p))role="LAB";
  else if("transport@campuscare.com".equals(e)&&"1234".equals(p))role="TRANSPORT";
  else if("library@campuscare.com".equals(e)&&"1234".equals(p))role="LIBRARY";
  else if("maintenance@campuscare.com".equals(e)&&"1234".equals(p))role="MAINTENANCE";
  else if("security@campuscare.com".equals(e)&&"1234".equals(p))role="SECURITY";
  if(role!=null){q.getSession().setAttribute("adminRole",role);r.sendRedirect("admin.jsp");}else r.sendRedirect("admin-login.jsp?error=1");
 }}