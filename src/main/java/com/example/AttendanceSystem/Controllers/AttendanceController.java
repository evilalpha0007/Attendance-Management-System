package com.example.AttendanceSystem.Controllers;

import com.example.AttendanceSystem.Entities.Attendance;
import com.example.AttendanceSystem.Entities.Classes;
import com.example.AttendanceSystem.Entities.Students;
import com.example.AttendanceSystem.Repository.AttendanceRepo;
import com.example.AttendanceSystem.Repository.ClassesRepo;
import com.example.AttendanceSystem.Service.AttendanceService;
import com.example.AttendanceSystem.Service.StudentService;
import com.example.AttendanceSystem.Service.classService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

        @Autowired
        private AttendanceService attendanceService;

        @Autowired
        private classService classServe;

        @Autowired
        private StudentService studentService;

        @Autowired
        private AttendanceRepo attendanceRepo;

        @Autowired
        private ClassesRepo classesRepo;

    @GetMapping("/class/{classid}")
    public ResponseEntity<List<Attendance>> getAttendanceByClass(@PathVariable ObjectId classid) {

        try {
            Optional<Classes> class1;
            class1=classesRepo.findById(classid);
            List<Students> studentsList = class1.get().getStudentsList();
            List<Attendance> attendanceList = null;
            for (Students s:studentsList){
                attendanceList=s.getAttendance();
            }
            if (attendanceList==null){
                throw new RuntimeException("No class found");
            }
            return new ResponseEntity<>(attendanceList,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/add-attendance")
    public ResponseEntity<Attendance> addAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = attendanceService.addAttendance(attendance);
            return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-attendance/{studentId}")
    public ResponseEntity<List<Attendance>> getAttendanceFromStudent(@PathVariable ObjectId studentId) {
        List<Attendance> attendanceList = studentService.getAttendanceFromStudent(studentId);
        return ResponseEntity.ok(attendanceList);
    }


}

