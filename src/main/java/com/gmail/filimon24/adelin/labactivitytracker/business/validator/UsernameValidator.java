package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.UsernameAlreadyExistsException;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class UsernameValidator implements Validator<String> {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final Pattern pattern = Pattern.compile(CustomApplicationProperties.USERNAME_REGEX);

    @Override
    public Boolean isValid(String username) throws UsernameAlreadyExistsException {
        Matcher matcher = pattern.matcher(username);
        if (!matcher.matches()) return false;

        if (teacherRepository.existsTeacherByUsername(username) || studentRepository.existsStudentByUsername(username))
            throw new UsernameAlreadyExistsException(username);

        return true;
    }
}
