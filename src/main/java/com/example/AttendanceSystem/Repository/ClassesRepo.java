package com.example.AttendanceSystem.Repository;

import com.example.AttendanceSystem.Entities.Classes;
import com.example.AttendanceSystem.Entities.Students;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ClassesRepo extends MongoRepository<Classes, ObjectId> {

    @Query("{ 'className' : { $regex: ?0, $options: 'i' } }")
    Classes findByName(String name);
}
