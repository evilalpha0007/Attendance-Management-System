package com.example.AttendanceSystem.Controllers;

import com.example.AttendanceSystem.Entities.Teacher;
import com.example.AttendanceSystem.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private TeacherService teacherService;


        @GetMapping("/getall")
        public ResponseEntity<?> getEveryTeacher(){
            if (teacherService.getAllTeachers().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<Teacher> all=teacherService.getAllTeachers();
            return new ResponseEntity<>(all,HttpStatus.OK);
        }

    @PostMapping("/createadmin")
    public ResponseEntity<?> createAdmin(@RequestBody Teacher teacher){
        teacherService.saveNewAdmin(teacher);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
