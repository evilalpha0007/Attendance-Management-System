package com.example.AttendanceSystem.Service;

import com.example.AttendanceSystem.Entities.Classes;
import com.example.AttendanceSystem.Entities.Students;
import com.example.AttendanceSystem.Repository.ClassesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/class")
public class classService  {

    @Autowired
    private ClassesRepo classesRepo;

    @Autowired
    private Classes classes;

    public void AddClass(Classes classes,String username){
        Classes existing_class= (classesRepo.findByName(classes.getName()));
        if (existing_class!=null){
            throw new RuntimeException(" a class of this name already exist");
        }
        classesRepo.save(classes);
    }

    public void saveoldclass(Classes classes){
        classesRepo.save(classes);
    }
    public Classes findbyclasname(String classname){
        return classesRepo.findByName(classname);
    }
}
