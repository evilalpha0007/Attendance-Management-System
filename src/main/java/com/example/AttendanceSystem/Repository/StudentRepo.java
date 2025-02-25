package com.example.AttendanceSystem.Repository;

import com.example.AttendanceSystem.Entities.Students;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Students, ObjectId> {
    Students findByEmail(String email);
}
