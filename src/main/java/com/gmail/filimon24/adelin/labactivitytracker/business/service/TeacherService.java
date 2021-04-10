package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.CustomApplicationProperties;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.TeacherMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.TokenMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.TeacherDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TeacherRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TokenRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Token;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;
    private final TeacherMapper teacherMapper;

    public void insertInitialTeachers() {
        for (TeacherDto teacherDto : CustomApplicationProperties.initialTeachers) {
            if (!teacherRepository.existsTeacherByEmail(teacherDto.getEmail())) {
                Teacher teacher = teacherMapper.dtoToDao(teacherDto);
                teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
                teacherRepository.save(teacher);
            }
        }
    }

    public TokenDto generateToken() {
        String generatedString = RandomString.make(128);
        Token token = Token.builder().token(generatedString).build();
        token = tokenRepository.save(token);
        return tokenMapper.daoToDto(token);
    }
}
