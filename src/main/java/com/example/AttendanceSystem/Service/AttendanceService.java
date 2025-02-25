package com.example.AttendanceSystem.Service;

import com.example.AttendanceSystem.Entities.Attendance;
import com.example.AttendanceSystem.Entities.Students;
import com.example.AttendanceSystem.Repository.AttendanceRepo;
import com.example.AttendanceSystem.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepository;


    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepo studentRepository;


    public List<Attendance> getAttendanceByClass(String className) {
        return attendanceRepository.findByclassName(className);
    }

    public List<Attendance> getAttendanceByClassAndDate(String className, LocalDate date) {
        return attendanceRepository.findByClassNameAndDate(className, date);
    }

    public Attendance addAttendance(Attendance attendance) {


        // Step 2: Update the corresponding student document
        Optional<Students> optionalStudent = studentRepository.findById(attendance.getStudentId());
        if (optionalStudent.isPresent()) {
            Students student = optionalStudent.get();
            Attendance savedAttendance = attendanceRepository.save(attendance);
            attendance.setDate(LocalDate.now());
            attendance.setClassName(student.getClassName());
            savedAttendance.setStudentName(student.getName());
            student.getAttendance().add(savedAttendance); // Add attendance to the student's record
            studentRepository.save(student);
            return savedAttendance;
        } else {
            throw new RuntimeException("Student with ID " + attendance.getStudentId() + " not found.");
        }


    }







}
