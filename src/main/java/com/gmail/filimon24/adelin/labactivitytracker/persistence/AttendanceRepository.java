package com.gmail.filimon24.adelin.labactivitytracker.persistence;

import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Attendance;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceId> {
}
