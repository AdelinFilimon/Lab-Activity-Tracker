package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Attendance;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
    List<Attendance> findAttendancesByLaboratoryClassId(Long laboratoryClassId);
}
