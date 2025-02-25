package com.example.AttendanceSystem.Repository;

import com.example.AttendanceSystem.Entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class teacherRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Teacher> userForSA(){
        Query query=new Query();

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));


        List<Teacher> users = mongoTemplate.find(query, Teacher.class);
        return users;

    }
}
