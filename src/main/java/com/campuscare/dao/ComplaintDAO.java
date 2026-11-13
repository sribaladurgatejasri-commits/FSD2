package com.campuscare.dao;
import java.sql.*; import java.util.*; import com.campuscare.model.Complaint; import com.campuscare.util.DBConnection;
public class ComplaintDAO {
 public boolean create(int studentId,String category,String title,String desc,String location,String priority){
  String q1="SELECT category_id,responsible_role FROM complaint_categories WHERE category_name=?";
  String q2="SELECT admin_id FROM complaint_admins WHERE admin_role=?";
  String q3="INSERT INTO complaints(student_id,category_id,admin_id,title,description,location,priority) VALUES(?,?,?,?,?,?,?)";
  try(Connection c=DBConnection.getConnection()){int cid=0,aid=0;String role=null;
   try(PreparedStatement p=c.prepareStatement(q1)){p.setString(1,category);try(ResultSet r=p.executeQuery()){if(r.next()){cid=r.getInt(1);role=r.getString(2);}}}
   try(PreparedStatement p=c.prepareStatement(q2)){p.setString(1,role);try(ResultSet r=p.executeQuery()){if(r.next())aid=r.getInt(1);}}
   if(cid==0||aid==0)return false;
   try(PreparedStatement p=c.prepareStatement(q3)){p.setInt(1,studentId);p.setInt(2,cid);p.setInt(3,aid);p.setString(4,title);p.setString(5,desc);p.setString(6,location);p.setString(7,priority);return p.executeUpdate()>0;}
  }catch(Exception e){e.printStackTrace();return false;}
 }
 public List<Complaint> studentList(int sid){List<Complaint> a=new ArrayList<>();String q="SELECT c.complaint_id,c.title,c.description,c.location,c.priority,c.status,c.admin_remarks,cc.category_name FROM complaints c JOIN complaint_categories cc ON c.category_id=cc.category_id WHERE c.student_id=? ORDER BY c.created_at DESC";
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setInt(1,sid);try(ResultSet r=p.executeQuery()){while(r.next()){Complaint x=new Complaint();x.setComplaintId(r.getInt(1));x.setTitle(r.getString(2));x.setDescription(r.getString(3));x.setLocation(r.getString(4));x.setPriority(r.getString(5));x.setStatus(r.getString(6));x.setRemarks(r.getString(7));x.setCategoryName(r.getString(8));a.add(x);}}}catch(Exception e){e.printStackTrace();}return a;}
 public List<Complaint> adminList(String role){List<Complaint> a=new ArrayList<>();String q="SELECT c.complaint_id,c.title,c.description,c.location,c.priority,c.status,c.admin_remarks,cc.category_name FROM complaints c JOIN complaint_categories cc ON c.category_id=cc.category_id JOIN complaint_admins ad ON c.admin_id=ad.admin_id WHERE ad.admin_role=? ORDER BY c.created_at DESC";
  try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setString(1,role);try(ResultSet r=p.executeQuery()){while(r.next()){Complaint x=new Complaint();x.setComplaintId(r.getInt(1));x.setTitle(r.getString(2));x.setDescription(r.getString(3));x.setLocation(r.getString(4));x.setPriority(r.getString(5));x.setStatus(r.getString(6));x.setRemarks(r.getString(7));x.setCategoryName(r.getString(8));a.add(x);}}}catch(Exception e){e.printStackTrace();}return a;}
 public boolean update(int id,String status,String remarks){String q="UPDATE complaints SET status=?,admin_remarks=?,updated_at=CURRENT_TIMESTAMP WHERE complaint_id=?";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(q)){p.setString(1,status);p.setString(2,remarks);p.setInt(3,id);return p.executeUpdate()>0;}catch(Exception e){e.printStackTrace();return false;}}
}