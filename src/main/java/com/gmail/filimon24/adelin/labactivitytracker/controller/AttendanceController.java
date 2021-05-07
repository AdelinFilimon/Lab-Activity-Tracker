package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.AttendanceAlreadyExistsException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.AttendanceNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.AttendanceService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.AttendanceForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.AttendanceDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.AttendanceId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<?> createAttendance(@RequestBody AttendanceForm attendanceForm) {

        try {
            attendanceService.create(attendanceForm.toAttendanceDto());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AttendanceAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAttendances() {
        try {
            List<AttendanceForm> attendances = attendanceService.getAll()
                    .stream()
                    .map(AttendanceForm::fromAttendanceDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(attendances, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{labId}")
    public ResponseEntity<?> getAttendancesOfLabClass(@PathVariable Long labId) {
        try {
            List<AttendanceForm> attendances = attendanceService.getAttendancesByLabId(labId)
                    .stream()
                    .map(AttendanceForm::fromAttendanceDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(attendances, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{labId}/{studentId}")
    public ResponseEntity<?> getAttendance(@PathVariable Long labId, @PathVariable Long studentId) {
        try {
            AttendanceId attendanceId = AttendanceId.builder()
                    .laboratoryClass(labId)
                    .student(studentId)
                    .build();
            AttendanceDto attendanceDto = attendanceService.get(attendanceId);
            AttendanceForm attendanceForm = AttendanceForm.fromAttendanceDto(attendanceDto);
            return new ResponseEntity<>(attendanceForm, HttpStatus.OK);
        }
        catch (AttendanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{labId}/{studentId}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long labId, @PathVariable Long studentId, @RequestBody AttendanceForm attendanceForm) {
        try {
            AttendanceId attendanceId = AttendanceId.builder()
                    .student(studentId)
                    .laboratoryClass(labId)
                    .build();

            AttendanceDto attendanceDto = AttendanceDto.builder()
                    .attendance(attendanceForm.getAttendance())
                    .build();

            attendanceService.update(attendanceId, attendanceDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AttendanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAttendances() {
        try {
            attendanceService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{labId}/{studentId}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long labId, @PathVariable Long studentId) {
        try {
            AttendanceId attendanceId = AttendanceId.builder()
                    .student(studentId)
                    .laboratoryClass(labId)
                    .build();
            attendanceService.delete(attendanceId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AttendanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
