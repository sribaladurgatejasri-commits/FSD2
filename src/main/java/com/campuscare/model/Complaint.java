package com.campuscare.model;
public class Complaint {
 private int complaintId; private String title,description,location,categoryName,priority,status,remarks;
 public int getComplaintId(){return complaintId;} public void setComplaintId(int v){complaintId=v;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public String getLocation(){return location;} public void setLocation(String v){location=v;}
 public String getCategoryName(){return categoryName;} public void setCategoryName(String v){categoryName=v;}
 public String getPriority(){return priority;} public void setPriority(String v){priority=v;}
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
 public String getRemarks(){return remarks;} public void setRemarks(String v){remarks=v;}
}