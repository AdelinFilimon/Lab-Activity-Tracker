package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.FieldType;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.StudentNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.StudentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.EmailValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.GroupValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.PasswordValidator;
import com.gmail.filimon24.adelin.labactivitytracker.business.validator.UsernameValidator;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentGroupDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentGroupRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.StudentGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService implements BasicService<StudentDto, Long>, UpdateEntityService<StudentDto, Long> {

    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final PasswordEncoder passwordEncoder;

    private final GroupValidator groupValidator;
    private final EmailValidator emailValidator;
    private final UsernameValidator usernameValidator;
    private final PasswordValidator passwordValidator;

    private final StudentMapper studentMapper;

    @Override
    public Object create(Object student) {
        StudentDto studentDto = (StudentDto) student;
        String email = studentDto.getEmail();
        Long groupNumber = studentDto.getStudentGroup().getGroupNumber();
        if (!groupValidator.isValid(groupNumber))
            throw new InvalidFieldException(FieldType.STUDENT_GROUP, groupNumber.toString());
        if (!emailValidator.isValid(email))
            throw new InvalidFieldException(FieldType.EMAIL, email);

        Long studentGroupId = studentGroupRepository.findStudentGroupByGroupNumber(groupNumber).getId();
        studentDto.getStudentGroup().setId(studentGroupId);
        Student studentDao = studentRepository.save(studentMapper.dataAccessToEntity(studentDto));
        return studentMapper.entityToDataAccess(studentDao);
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto get(Long id) {
        Student student =  studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.entityToDataAccess(student);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }

    @Override
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) throw new StudentNotFoundException(id);
        studentRepository.deleteById(id);
    }

    @Override
    public void update(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));

        String username = studentDto.getUsername();
        String email = studentDto.getEmail();
        String password = studentDto.getPassword();
        StudentGroupDto studentGroupDto = studentDto.getStudentGroup();

        String firstName = studentDto.getFirstName();
        String lastName = studentDto.getLastName();
        String hobby = studentDto.getHobby();

        if (username != null) {
            if (usernameValidator.isValid(username)) student.setUsername(username);
            else throw new InvalidFieldException(FieldType.USERNAME, username);
        }

        if (email != null) {
            if (emailValidator.isValid(email)) student.setEmail(email);
            else throw new InvalidFieldException(FieldType.EMAIL, email);
        }

        if (password != null) {
            if (passwordValidator.isValid(password)) student.setPassword(passwordEncoder.encode(password));
            else throw new InvalidFieldException(FieldType.PASSWORD, password);
        }

        if (studentGroupDto != null) {
            if (studentGroupDto.getGroupNumber() != null && groupValidator.isValid(studentGroupDto.getGroupNumber()))
                student.setStudentGroup(studentGroupRepository.findStudentGroupByGroupNumber(studentGroupDto.getGroupNumber()));
            else {
                if (studentGroupDto.getId() != null) {
                    StudentGroup studentGroup = studentGroupRepository.findById(studentGroupDto.getId())
                            .orElseThrow(() -> new InvalidFieldException(FieldType.STUDENT_GROUP,
                                    studentGroupDto.getGroupNumber().toString()));
                    student.setStudentGroup(studentGroup);
                }
            }
        }

        if (firstName != null) {
            student.setFirstName(firstName);
        }

        if (lastName != null) {
            student.setLastName(lastName);
        }

        if (hobby != null) {
            student.setHobby(hobby);
        }

        studentRepository.save(student);
    }

    public void registerStudent(StudentDto studentDto) {

        if(!usernameValidator.isValid(studentDto.getUsername()))
            throw new InvalidFieldException(FieldType.USERNAME, studentDto.getUsername());

        if(!passwordValidator.isValid(studentDto.getPassword()))
            throw new InvalidFieldException(FieldType.PASSWORD, studentDto.getPassword());

        studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));

        studentRepository.save(studentMapper.dataAccessToEntity(studentDto));
    }
}
