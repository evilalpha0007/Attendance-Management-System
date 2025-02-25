package com.example.AttendanceSystem.Entities;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Teachers")
public class Teacher {



    @Indexed(unique = true)
    private String name;

    private String email;


    private String password;

    @Id
    private ObjectId id;


    List<String> classesList=new ArrayList<>();

    List<String> Roles=new ArrayList<>();

    public List<String> getRoles() {
        return Roles;
    }

    public void setRoles(List<String> roles) {
        Roles = roles;
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


    public String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public List<String> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<String> classesList) {
        this.classesList = classesList;
    }
}
