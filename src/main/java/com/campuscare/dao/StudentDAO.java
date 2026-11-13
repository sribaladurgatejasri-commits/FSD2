package com.campuscare.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.campuscare.model.Student;
import com.campuscare.util.DBConnection;

public class StudentDAO {

    public boolean register(Student student) {

        String sql = "INSERT INTO students "
                + "(roll_no, name, email, phone, department, year_no, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Database connection failed.");
                return false;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getDepartment());
            ps.setInt(6, student.getYearNo());
            ps.setString(7, student.getPassword());

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            System.out.println("Registration database error:");
            e.printStackTrace();

            return false;
        }
    }


    public Student login(String email, String password) {

        String sql = "SELECT * FROM students "
                + "WHERE email = ? AND password = ?";

        try {

            Connection con = DBConnection.getConnection();

            if (con == null) {
                return null;
            }

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Student student = new Student();

                student.setStudentId(
                        rs.getInt("student_id"));

                student.setRollNo(
                        rs.getString("roll_no"));

                student.setName(
                        rs.getString("name"));

                student.setEmail(
                        rs.getString("email"));

                student.setPhone(
                        rs.getString("phone"));

                student.setDepartment(
                        rs.getString("department"));

                student.setYearNo(
                        rs.getInt("year_no"));

                rs.close();
                ps.close();
                con.close();

                return student;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}