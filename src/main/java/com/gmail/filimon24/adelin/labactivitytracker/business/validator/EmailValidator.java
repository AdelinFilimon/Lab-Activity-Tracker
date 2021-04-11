package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.EmailAlreadyUsed;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class EmailValidator implements Validator<String> {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final Pattern pattern = Pattern.compile(CustomApplicationProperties.emailRegex);

    @Override
    public Boolean isValid(String email) throws EmailAlreadyUsed {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) return false;

        if (teacherRepository.existsTeacherByEmail(email) || studentRepository.existsStudentByEmail(email))
            throw new EmailAlreadyUsed(email);

        return true;
    }
}
