package com.gmail.filimon24.adelin.labactivitytracker.business.validator;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Validator<String> {

    private final Pattern pattern = Pattern.compile(CustomApplicationProperties.PASSWORD_REGEX);

    @Override
    public Boolean isValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
