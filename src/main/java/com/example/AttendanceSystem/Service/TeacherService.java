package com.example.AttendanceSystem.Service;

import com.example.AttendanceSystem.Entities.Teacher;
import com.example.AttendanceSystem.Repository.TeacherRepo;
import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherService {

    @Autowired
    private TeacherRepo teacherRepo;

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    @Transactional
    public Teacher addNewTeacher(Teacher teacher) {
        if (teacher.getName() == null || teacher.getName().trim().isEmpty()) {
            throw new RuntimeException("Name cannot be null or empty.");
        }

        // Check if a teacher with the same name already exists
        Teacher existingTeacher = teacherRepo.findByEmail(teacher.getEmail());
        if (existingTeacher!=null){
            throw new RuntimeException("A teacher with this Email already exists.");
        }
        try {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacher.setRoles(Arrays.asList("Teacher"));
            return teacherRepo.save(teacher);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Duplicate key error: A teacher with this name already exists.");
        }
    }


    public void saveNewAdmin(Teacher teacher1){
        teacher1.setPassword(passwordEncoder.encode(teacher1.getPassword()));
        teacher1.setRoles(Arrays.asList("Teacher","ADMIN"));
        teacherRepo.save(teacher1);
    }

    public Optional<Teacher> getTeacherById(ObjectId id) {
        return teacherRepo.findById(id);
    }

    public Teacher updateTeacher(ObjectId id, Teacher updatedTeacher) {
        Optional<Teacher> optionalTeacher = teacherRepo.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setName(updatedTeacher.getName());
            teacher.setEmail(updatedTeacher.getEmail());
            teacher.setClassesList(updatedTeacher.getClassesList());
            return teacherRepo.save(teacher);
        } else {
            throw new RuntimeException("Teacher with ID " + id + " not found.");
        }
    }

    public void deleteTeacher(ObjectId id) {
        teacherRepo.deleteById(id);
    }

    public List<Teacher> getAllTeachers(){
        return teacherRepo.findAll();
    }
}
