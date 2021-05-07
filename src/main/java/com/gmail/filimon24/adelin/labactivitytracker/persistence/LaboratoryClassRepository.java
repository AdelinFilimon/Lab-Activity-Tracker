package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.LaboratoryClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryClassRepository extends JpaRepository<LaboratoryClass, Long> {
    Boolean existsByLabNumber(Integer labNumber);
    LaboratoryClass findLaboratoryClassByLabNumber(Integer labNumber);
    LaboratoryClass findLaboratoryClassByTitle(String title);
}
