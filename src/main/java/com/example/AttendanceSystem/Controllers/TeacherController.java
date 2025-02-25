package com.example.AttendanceSystem.Controllers;

import com.example.AttendanceSystem.Entities.Teacher;
import com.example.AttendanceSystem.Service.TeacherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;



    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable ObjectId id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        if(teacher.isPresent()){
            return new ResponseEntity<>(teacher,HttpStatus.OK);
        }
       throw new RuntimeException("Teacher not found");
    }
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable ObjectId id, @RequestBody Teacher teacher) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
            return ResponseEntity.ok(updatedTeacher);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable ObjectId id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok("Teacher with ID " + id + " has been deleted.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
