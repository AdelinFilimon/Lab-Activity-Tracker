package com.gmail.filimon24.adelin.labactivitytracker.service.mapper;

import com.gmail.filimon24.adelin.labactivitytracker.model.AttendanceDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Attendance;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AttendanceMapper implements Mapper<Attendance, AttendanceDto> {

    private final StudentMapper studentMapper;
    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public AttendanceDto daoToDto(Attendance attendance) {
        return AttendanceDto.builder()
                .student(studentMapper.daoToDto(attendance.getStudent()))
                .attendance(attendance.getAttendance().getCode())
                .laboratoryClass(laboratoryClassMapper.daoToDto(attendance.getLaboratoryClass()))
                .build();
    }

}
