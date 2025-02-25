package com.example.AttendanceSystem.Controllers;


import com.example.AttendanceSystem.Entities.Teacher;
import com.example.AttendanceSystem.Service.EmailService;
import com.example.AttendanceSystem.Service.TeacherDetailServiceImpl;
import com.example.AttendanceSystem.Service.TeacherService;
import com.example.AttendanceSystem.utilis.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicConntroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TeacherDetailServiceImpl teacherDetailService;

    @Autowired
    private TeacherService teacherService;


    @Autowired
    private EmailService emailService;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Teacher teacher1) {
        try {
            Teacher savedTeacher=teacherService.addNewTeacher(teacher1);
            String body="Hello " + teacher1.getName() + ",\n\nThank you for signing up!\n\nBest Regards,\nYour Team";
            emailService.sendEmail(teacher1.getEmail()," Welcome to Our Platform",body);
            return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Teacher teacher1) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(teacher1.getEmail(),teacher1.getPassword()));
            UserDetails userDetails=teacherDetailService.loadUserByUsername(teacher1.getEmail());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
