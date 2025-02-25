package com.example.AttendanceSystem.Repository;

import com.example.AttendanceSystem.Entities.Teacher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepo extends MongoRepository<Teacher, ObjectId>{
    Teacher findByEmail(String email);
    Teacher findByName(String name);
}
