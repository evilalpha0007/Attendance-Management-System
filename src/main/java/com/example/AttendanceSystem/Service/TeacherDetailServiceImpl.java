package com.example.AttendanceSystem.Service;


import com.example.AttendanceSystem.Entities.Teacher;
import com.example.AttendanceSystem.Repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherDetailServiceImpl implements UserDetailsService {

    @Autowired
    private TeacherRepo teacherRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher=teacherRepo.findByEmail(email);
        if (teacher!=null){
            UserDetails userDetails=org.springframework.security.core.userdetails.User.builder().username(teacher.getEmail()).password(teacher.getPassword()).roles(teacher.getRoles().toArray(new String[0])).build();
            return userDetails;
        }
        throw new UsernameNotFoundException("teacher not found with email: " + email);
    }
}
