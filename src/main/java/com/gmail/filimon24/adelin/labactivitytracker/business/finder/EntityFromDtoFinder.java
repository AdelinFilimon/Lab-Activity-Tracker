package com.gmail.filimon24.adelin.labactivitytracker.business.finder;

import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Assignment;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;

public interface EntityFromDtoFinder {
    LaboratoryClass findLaboratoryClass(LaboratoryClassDto laboratoryClassDto);
    Student findStudent(StudentDto studentDto);
    Assignment findAssignment(AssignmentDto assignmentDto);
}
