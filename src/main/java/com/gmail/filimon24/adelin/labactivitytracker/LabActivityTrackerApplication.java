package com.gmail.filimon24.adelin.labactivitytracker;

import com.gmail.filimon24.adelin.labactivitytracker.business.service.StudentGroupService;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LabActivityTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabActivityTrackerApplication.class, args);
    }

    private TeacherService teacherService;
    private StudentGroupService studentGroupService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setStudentGroupService(StudentGroupService studentGroupService) {
        this.studentGroupService = studentGroupService;
    }

    @Bean
    public CommandLineRunner insertInitialTeachers() {
        return args -> teacherService.insertInitialTeachers();
    }

    @Bean
    public CommandLineRunner insertInitialGroups() {
        return args -> studentGroupService.insertInitialGroups();
    }

}
