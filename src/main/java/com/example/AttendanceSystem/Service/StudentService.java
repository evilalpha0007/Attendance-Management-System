package com.example.AttendanceSystem.Service;

import com.example.AttendanceSystem.Entities.Attendance;
import com.example.AttendanceSystem.Entities.Classes;
import com.example.AttendanceSystem.Entities.Students;
import com.example.AttendanceSystem.Repository.AttendanceRepo;
import com.example.AttendanceSystem.Repository.ClassesRepo;
import com.example.AttendanceSystem.Repository.StudentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
public class StudentService {
    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private ClassesRepo classRepository;

    @Autowired
    private classService classService;

    @Autowired
    private AttendanceRepo attendanceRepo;

    // Add a new student
    @Transactional
    public void addStudent(String className, Students student) {
        Students existing_student = (studentRepository.findByEmail(student.getEmail()));
        if (existing_student != null) {
            throw new RuntimeException("A user with this email already exists.");
        }
        studentRepository.save(student);

        // Find the class and add the student to the class
        Classes existingClass = classRepository.findByName(className);
        if (existingClass != null) {
            existingClass.getStudentsList().add(student);
            classRepository.save(existingClass);
        } else {
            // If the class doesn't exist, create a new one
            Classes newClass = new Classes();
            newClass.setName(className);
            newClass.getStudentsList().add(student);
            classRepository.save(newClass);

        }

    }

    public Optional<Students> findStudentById(ObjectId id) {
        return studentRepository.findById(id);
    }

    public ResponseEntity<?> removeStudentbyId(ObjectId id) {
        Optional<Students> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Students student = optionalStudent.get();


            studentRepository.deleteById(id);


            attendanceRepo.deleteByStudentId(id);

            // Step 4: Remove the student ID from the class's student list
            Classes classEntity = classRepository.findByName(student.getClassName());
            classEntity.getStudentsList().remove(student); // Remove student ID
            classRepository.save(classEntity); // Save updated class document
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            throw new RuntimeException("Student with ID " + id + " not found.");
        }
    }


    public List<Students> getall() {
        return studentRepository.findAll();

    }

    public List<Attendance> getAttendanceById(ObjectId studentId) {
        return attendanceRepo.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceFromStudent(ObjectId studentId) {
        Optional<Students> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get().getAttendance(); // Return embedded attendance records
        } else {
            throw new RuntimeException("Student with ID " + studentId + " not found.");
        }
    }
}
