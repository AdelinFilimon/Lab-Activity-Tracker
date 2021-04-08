package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryClassRepository extends JpaRepository<LaboratoryClass, Long> {
}
