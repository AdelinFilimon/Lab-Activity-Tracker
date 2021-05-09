package com.gmail.filimon24.adelin.labactivitytracker;

public final class CustomApplicationProperties {

    public final static String ATTENDANCE_COLUMN_DEFINITION = "ENUM('PRESENT', 'ABSENT', 'LATE')"; //Changing this needs code refactoring
    public final static String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    public final static String STUDENT_ROLE_IDENTIFIER = "STUDENT";
    public final static String TEACHER_ROLE_IDENTIFIER = "TEACHER";

    public final static String USERNAME_REGEX = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public final static String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    //Schema generation properties
    public final static int BIG_FIELD_LEN = 64;
    public final static int MEDIUM_FIELD_LEN = 32;
    public final static int SMALL_FIELD_LEN = 16;
    public final static int PASSWORD_FIELD_LEN = 60; //BCryptPasswordEncoder returns 60 characters long string
    public final static int TOKEN_FIELD_LEN = 128;
    public final static int GROUP_NR_LEN = 5;
}
