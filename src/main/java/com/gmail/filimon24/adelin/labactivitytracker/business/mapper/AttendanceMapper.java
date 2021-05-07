package com.gmail.filimon24.adelin.labactivitytracker.business.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.AttendanceDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Attendance;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AttendanceMapper implements ModelMapper<Attendance, AttendanceDto> {

    private final StudentMapper studentMapper;
    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public AttendanceDto entityToDataAccess(Attendance attendance) {
        return AttendanceDto.builder()
                .student(studentMapper.entityToDataAccess(attendance.getStudent()))
                .attendance(attendance.getAttendance().getCode())
                .laboratoryClass(laboratoryClassMapper.entityToDataAccess(attendance.getLaboratoryClass()))
                .build();
    }

    @Override
    public Attendance dataAccessToEntity(AttendanceDto attendanceDto) {
        return Attendance.builder()
                .student(studentMapper.dataAccessToEntity(attendanceDto.getStudent()))
                .attendance(AttendanceType.valueOf(attendanceDto.getAttendance()))
                .laboratoryClass(laboratoryClassMapper.dataAccessToEntity(attendanceDto.getLaboratoryClass()))
                .build();
    }
}
