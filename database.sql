-- Run this while connected as GRIEVANCE/grievance123
CREATE TABLE students (
 student_id NUMBER PRIMARY KEY, roll_no VARCHAR2(30) UNIQUE NOT NULL,
 name VARCHAR2(100) NOT NULL, email VARCHAR2(100) UNIQUE NOT NULL,
 phone VARCHAR2(20), department VARCHAR2(100), year_no NUMBER,
 password VARCHAR2(255) NOT NULL);
CREATE SEQUENCE student_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE OR REPLACE TRIGGER student_trigger BEFORE INSERT ON students FOR EACH ROW
BEGIN IF :NEW.student_id IS NULL THEN SELECT student_seq.NEXTVAL INTO :NEW.student_id FROM dual; END IF; END;
/
CREATE TABLE complaint_admins (
 admin_id NUMBER PRIMARY KEY, admin_name VARCHAR2(100) NOT NULL,
 email VARCHAR2(100) UNIQUE NOT NULL, password VARCHAR2(255) NOT NULL,
 admin_role VARCHAR2(50) NOT NULL);
CREATE SEQUENCE admin_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE OR REPLACE TRIGGER admin_trigger BEFORE INSERT ON complaint_admins FOR EACH ROW
BEGIN IF :NEW.admin_id IS NULL THEN SELECT admin_seq.NEXTVAL INTO :NEW.admin_id FROM dual; END IF; END;
/
CREATE TABLE complaint_categories (
 category_id NUMBER PRIMARY KEY, category_name VARCHAR2(100) NOT NULL,
 responsible_role VARCHAR2(50) NOT NULL);
CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE OR REPLACE TRIGGER category_trigger BEFORE INSERT ON complaint_categories FOR EACH ROW
BEGIN IF :NEW.category_id IS NULL THEN SELECT category_seq.NEXTVAL INTO :NEW.category_id FROM dual; END IF; END;
/
CREATE TABLE complaints (
 complaint_id NUMBER PRIMARY KEY, student_id NUMBER NOT NULL, category_id NUMBER NOT NULL,
 admin_id NUMBER, title VARCHAR2(200) NOT NULL, description VARCHAR2(2000) NOT NULL,
 location VARCHAR2(200), priority VARCHAR2(20) DEFAULT 'Medium', status VARCHAR2(30) DEFAULT 'Submitted',
 admin_remarks VARCHAR2(1000), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 CONSTRAINT fk_cc_student FOREIGN KEY(student_id) REFERENCES students(student_id),
 CONSTRAINT fk_cc_category FOREIGN KEY(category_id) REFERENCES complaint_categories(category_id),
 CONSTRAINT fk_cc_admin FOREIGN KEY(admin_id) REFERENCES complaint_admins(admin_id));
CREATE SEQUENCE complaint_seq START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;
CREATE OR REPLACE TRIGGER complaint_trigger BEFORE INSERT ON complaints FOR EACH ROW
BEGIN IF :NEW.complaint_id IS NULL THEN SELECT complaint_seq.NEXTVAL INTO :NEW.complaint_id FROM dual; END IF; END;
/
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Hostel Warden','warden@campuscare.com','1234','HOSTEL');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Canteen Manager','canteen@campuscare.com','1234','CANTEEN');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Class Admin','classadmin@campuscare.com','1234','CLASSROOM');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Lab In-Charge','lab@campuscare.com','1234','LAB');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Transport Officer','transport@campuscare.com','1234','TRANSPORT');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Library Admin','library@campuscare.com','1234','LIBRARY');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Maintenance Admin','maintenance@campuscare.com','1234','MAINTENANCE');
INSERT INTO complaint_admins(admin_name,email,password,admin_role) VALUES('Security Officer','security@campuscare.com','1234','SECURITY');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Hostel','HOSTEL');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Canteen','CANTEEN');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Classroom','CLASSROOM');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Laboratory','LAB');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Transport','TRANSPORT');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Library','LIBRARY');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Maintenance','MAINTENANCE');
INSERT INTO complaint_categories(category_name,responsible_role) VALUES('Security','SECURITY');
COMMIT;