package com.gmail.filimon24.adelin.labactivitytracker.business.finder;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.*;
import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.AssignmentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.LaboratoryClassRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.StudentRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Assignment;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseFinder implements EntityFromDtoFinder {

    private final LaboratoryClassRepository laboratoryClassRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;

    @Override
    public LaboratoryClass findLaboratoryClass(LaboratoryClassDto laboratoryClassDto) {
        LaboratoryClass laboratoryClass;

        if (laboratoryClassDto == null)
            throw new LaboratoryClassNotFoundException();

        if (laboratoryClassDto.getId() != null) {
            laboratoryClass = laboratoryClassRepository.findById(laboratoryClassDto.getId())
                    .orElseThrow(() -> new InvalidFieldException(FieldType.ID, laboratoryClassDto.getId().toString()));
        } else if (laboratoryClassDto.getLabNumber() != null) {
            laboratoryClass = laboratoryClassRepository.findLaboratoryClassByLabNumber(laboratoryClassDto.getLabNumber());
            if (laboratoryClass == null)
                throw new InvalidFieldException(FieldType.LAB_NUMBER, laboratoryClassDto.getLabNumber().toString());
        } else if (laboratoryClassDto.getTitle() != null) {
            laboratoryClass = laboratoryClassRepository.findLaboratoryClassByTitle(laboratoryClassDto.getTitle());
            if (laboratoryClass == null)
                throw new InvalidFieldException(FieldType.LAB_NUMBER, laboratoryClassDto.getLabNumber().toString());
        } else {
            throw new InvalidFieldException(FieldType.ID, "");
        }

        return laboratoryClass;
    }

    @Override
    public Student findStudent(StudentDto studentDto) {
        Student student;

        if (studentDto == null)
            throw new StudentNotFoundException();

        if (studentDto.getId() != null)
            student = studentRepository.findById(studentDto.getId())
                    .orElseThrow(() -> new InvalidFieldException(FieldType.ID, studentDto.getId().toString()));
        else if (studentDto.getEmail() != null) {
            student = studentRepository.findStudentByEmail(studentDto.getEmail());
            if (student == null) throw new InvalidFieldException(FieldType.EMAIL, studentDto.getEmail());
        }
        else if (studentDto.getUsername() != null) {
            student = studentRepository.findStudentByUsername(studentDto.getUsername());
            if (student == null) throw new InvalidFieldException(FieldType.USERNAME, studentDto.getUsername());
        }
        else {
            throw new InvalidFieldException(FieldType.ID, "");
        }

        return student;
    }

    @Override
    public Assignment findAssignment(AssignmentDto assignmentDto) {
        Assignment assignment;

        if (assignmentDto == null)
            throw new AssignmentNotFoundException(-1L);

        if (assignmentDto.getId() != null)
            assignment = assignmentRepository.findById(assignmentDto.getId())
                    .orElseThrow(() -> new InvalidFieldException(FieldType.ID, assignmentDto.getId().toString()));
        else if (assignmentDto.getName() != null) {
            assignment = assignmentRepository.findAssignmentByName(assignmentDto.getName());
            if (assignment == null) throw new InvalidFieldException(FieldType.ASSIGNMENT_NAME, assignmentDto.getName());
        }
        else {
            throw new InvalidFieldException(FieldType.ID, "");
        }

        return assignment;
    }
}
