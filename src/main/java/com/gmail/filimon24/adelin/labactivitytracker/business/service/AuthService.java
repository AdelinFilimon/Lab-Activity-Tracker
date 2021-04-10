package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.service.mapper.StudentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.TokenDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentGroupRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.TokenRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final TokenRepository tokenRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StudentDto registerStudent(StudentDto studentDto, TokenDto tokenDto) {

        String token = tokenDto.getToken();
        if (tokenRepository.existsTokenByToken(token)) {

            studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));

            Long groupNumber = studentDto.getStudentGroup().getGroupNumber();

            Long id = studentGroupRepository.findStudentGroupByGroupNumber(groupNumber).getId();
            studentDto.getStudentGroup().setId(id);
            Student student = studentRepository.save(studentMapper.dtoToDao(studentDto));
            studentDto.setId(student.getId());
            tokenRepository.removeTokenByToken(token);
            return studentDto;
        }

        return null;
    }

}
