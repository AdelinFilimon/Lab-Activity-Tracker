package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
