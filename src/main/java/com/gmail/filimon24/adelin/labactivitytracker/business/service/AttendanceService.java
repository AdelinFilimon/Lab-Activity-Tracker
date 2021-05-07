package com.gmail.filimon24.adelin.labactivitytracker.business.service;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.AttendanceAlreadyExistsException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.AttendanceNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.finder.EntityFromDtoFinder;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.AttendanceMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.LaboratoryClassMapper;
import com.gmail.filimon24.adelin.labactivitytracker.business.mapper.StudentMapper;
import com.gmail.filimon24.adelin.labactivitytracker.model.AttendanceDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.AttendanceRepository;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.Attendance;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceId;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService implements BasicService<AttendanceDto, AttendanceId>, UpdateEntityService<AttendanceDto, AttendanceId> {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final StudentMapper studentMapper;
    private final EntityFromDtoFinder entityFinder;
    private final LaboratoryClassMapper laboratoryClassMapper;

    @Override
    public Object create(Object attendance) {
        AttendanceDto attendanceDto = (AttendanceDto) attendance;

        StudentDto studentDto = studentMapper.entityToDataAccess(entityFinder.findStudent(attendanceDto.getStudent()));
        LaboratoryClassDto laboratoryClassDto = laboratoryClassMapper.entityToDataAccess(entityFinder.findLaboratoryClass(attendanceDto.getLaboratoryClass()));

        AttendanceId attendanceId = AttendanceId.builder()
                .student(studentDto.getId())
                .laboratoryClass(laboratoryClassDto.getId())
                .build();

        if (attendanceRepository.existsById(attendanceId)) throw new AttendanceAlreadyExistsException(attendanceId);

        attendanceDto.setStudent(studentDto);
        attendanceDto.setLaboratoryClass(laboratoryClassDto);

        Attendance attendanceDao = attendanceRepository.save(attendanceMapper.dataAccessToEntity(attendanceDto));
        return attendanceMapper.entityToDataAccess(attendanceDao);
    }

    @Override
    public AttendanceDto get(AttendanceId id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new AttendanceNotFoundException(id));
        return attendanceMapper.entityToDataAccess(attendance);
    }

    @Override
    public List<AttendanceDto> getAll() {
        return attendanceRepository.findAll()
                .stream()
                .map(attendanceMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

    @Override
    public void update(AttendanceId attendanceId, AttendanceDto attendanceDto) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new AttendanceNotFoundException(attendanceId));

        if (attendanceDto.getAttendance() != null) {
            attendance.setAttendance(AttendanceType.valueOf(attendanceDto.getAttendance()));
        }

        attendanceRepository.save(attendance);
    }

    @Override
    public void delete(AttendanceId attendanceId) {
        if (!attendanceRepository.existsById(attendanceId)) throw new AttendanceNotFoundException(attendanceId);

        attendanceRepository.deleteById(attendanceId);
    }

    @Override
    public void deleteAll() {
        attendanceRepository.deleteAll();
    }

    public List<AttendanceDto> getAttendancesByLabId(Long laboratoryClassId) {
        return attendanceRepository.findAttendancesByLaboratoryClassId(laboratoryClassId)
                .stream()
                .map(attendanceMapper::entityToDataAccess)
                .collect(Collectors.toList());
    }

}
