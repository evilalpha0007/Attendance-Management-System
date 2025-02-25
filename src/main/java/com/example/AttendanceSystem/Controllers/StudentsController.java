package com.example.AttendanceSystem.Controllers;

import com.example.AttendanceSystem.Entities.Students;
import com.example.AttendanceSystem.Service.StudentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Students> addStudent(@RequestBody Students student) {
        try {
            String classname=student.getClassName();
            studentService.addStudent(classname,student);
            return new ResponseEntity<>( student,HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id){

            studentService.removeStudentbyId(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }
}
