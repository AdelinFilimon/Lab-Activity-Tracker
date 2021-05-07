package com.gmail.filimon24.adelin.labactivitytracker;

public final class CustomApplicationProperties {

    public final static String attendanceColumnDefinition = "ENUM('PRESENT', 'ABSENT', 'LATE')"; //Changing this needs code refactoring
    public final static String dateTimeFormat = "dd/MM/yyyy HH:mm";
    public final static String studentRoleIdentifier = "STUDENT";
    public final static String teacherRoleIdentifier = "TEACHER";

    public final static String usernameRegex = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public final static String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public final static String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    //Schema generation properties
    public final static int bigFieldLen = 64;
    public final static int mediumFieldLen = 32;
    public final static int smallFieldLen = 16;
    public final static int passwordFieldLen = 60; //BCryptPasswordEncoder returns 60 characters long string
    public final static int tokenFieldLen = 128;
    public final static int groupNrLen = 5;
}
