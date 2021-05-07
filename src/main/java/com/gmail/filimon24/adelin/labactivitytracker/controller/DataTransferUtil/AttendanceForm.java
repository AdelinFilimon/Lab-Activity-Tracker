package com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil;

import com.gmail.filimon24.adelin.labactivitytracker.model.AttendanceDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceForm {
    private String attendance;

    private String studentUsername;
    private String studentEmail;
    private Long studentId;

    private Long laboratoryId;
    private String laboratoryTitle;
    private Integer laboratoryNumber;

    public AttendanceDto toAttendanceDto() {
        StudentDto studentDto = StudentDto.builder()
                .id(studentId)
                .username(studentUsername)
                .email(studentEmail)
                .build();

        LaboratoryClassDto laboratoryClassDto = LaboratoryClassDto.builder()
                .id(laboratoryId)
                .labNumber(laboratoryNumber)
                .title(laboratoryTitle)
                .build();

        return AttendanceDto.builder()
                .student(studentDto)
                .laboratoryClass(laboratoryClassDto)
                .attendance(attendance)
                .build();
    }

    public static AttendanceForm fromAttendanceDto(AttendanceDto attendanceDto) {
        return AttendanceForm.builder()
                .attendance(attendanceDto.getAttendance())
                .laboratoryId(attendanceDto.getLaboratoryClass().getId())
                .laboratoryNumber(attendanceDto.getLaboratoryClass().getLabNumber())
                .laboratoryTitle(attendanceDto.getLaboratoryClass().getTitle())
                .studentId(attendanceDto.getStudent().getId())
                .studentEmail(attendanceDto.getStudent().getEmail())
                .studentUsername(attendanceDto.getStudent().getUsername())
                .build();
    }

}
