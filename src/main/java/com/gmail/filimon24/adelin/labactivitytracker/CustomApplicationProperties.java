package com.gmail.filimon24.adelin.labactivitytracker;

import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;

public final class CustomApplicationProperties {

    public final static String attendanceColumnDefinition = "ENUM('PRESENT', 'ABSENT', 'LATE')"; //Changing this needs code refactoring
    public final static String dateTimeFormat = "dd/MM/yyyy HH:mm";
    public final static String studentRoleIdentifier = "STUDENT";
    public final static String teacherRoleIdentifier = "TEACHER";

    public final static int bigFieldLen = 64;
    public final static int smallFieldLen = 16;
    public final static int passwordFieldLen = 60; //BCryptPasswordEncoder returns 60 characters long string
    public final static int tokenFieldLen = 128;

    public final static TeacherDto[] initialTeachers = {
            TeacherDto.builder().email("teacher@yahoo.com").firstName("First Name").lastName("Last Name").password("password").build()
    };

    public final static StudentGroupDto[] initialGroups = {
            StudentGroupDto.builder().groupNumber(30431L).build(),
            StudentGroupDto.builder().groupNumber(30432L).build(),
            StudentGroupDto.builder().groupNumber(30433L).build(),
            StudentGroupDto.builder().groupNumber(30434L).build()
    };

}
