package com.campuscare.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campuscare.dao.StudentDAO;
import com.campuscare.model.Student;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            String rollNo = request.getParameter("rollNo");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String department = request.getParameter("department");
            String yearNo = request.getParameter("yearNo");
            String password = request.getParameter("password");

            System.out.println("========== REGISTRATION ==========");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Phone: " + phone);
            System.out.println("Department: " + department);
            System.out.println("Year: " + yearNo);

            Student student = new Student();

            student.setRollNo(rollNo);
            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setDepartment(department);

            student.setYearNo(
                    Integer.parseInt(yearNo));

            student.setPassword(password);

            StudentDAO dao = new StudentDAO();

            boolean result = dao.register(student);

            if (result) {

                System.out.println(
                        "REGISTRATION SUCCESSFUL");

                response.sendRedirect(
                        "login.jsp?registered=1");

            } else {

                System.out.println(
                        "REGISTRATION FAILED");

                response.sendRedirect(
                        "register.jsp?error=1");
            }

        } catch (Exception e) {

            System.out.println(
                    "REGISTRATION ERROR");

            e.printStackTrace();

            response.sendRedirect(
                    "register.jsp?error=1");
        }
    }
}