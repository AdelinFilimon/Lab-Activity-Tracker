package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
