package com.example.AttendanceSystem.Repository;

import com.example.AttendanceSystem.Entities.Attendance;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepo extends MongoRepository<Attendance, ObjectId> {

    List<Attendance> findByclassName(String className); // Find attendance by class name

    List<Attendance> findByClassNameAndDate(String className, LocalDate date);

    List<Attendance> findByStudentId(ObjectId id);

    void deleteByStudentId(ObjectId studentId);

}
