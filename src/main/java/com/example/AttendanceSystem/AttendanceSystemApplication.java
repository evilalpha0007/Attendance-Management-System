package com.example.AttendanceSystem;

import com.example.AttendanceSystem.Entities.Classes;
import com.example.AttendanceSystem.Entities.Students;
import com.example.AttendanceSystem.Entities.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class AttendanceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceSystemApplication.class, args);
	}


	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory db) {
		return new MongoTransactionManager(db);
	}

	@Bean
	public Classes classes(){
		return new Classes();
	}

	@Bean
	public Teacher teacher(){
		return new Teacher();
	}


}
