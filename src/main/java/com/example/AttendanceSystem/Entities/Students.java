package com.example.AttendanceSystem.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = " Student")
public class Students {
    @Id
    private ObjectId id;

    private String name;

    private String email;

    private String className;

    int rollname;

    public int getRollname() {
        return rollname;
    }

    public void setRollname(int rollname) {
        this.rollname = rollname;
    }

    private List<Attendance> attendancerecords = new ArrayList<>();

    public List<Attendance> getAttendance() {
        return attendancerecords;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendancerecords = attendance;
    }

    public List<Attendance> getAttendancerecords() {
        return attendancerecords;
    }

    public void setAttendancerecords(List<Attendance> attendancerecords) {
        this.attendancerecords = attendancerecords;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}

