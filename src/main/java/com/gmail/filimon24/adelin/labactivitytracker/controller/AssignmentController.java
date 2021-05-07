package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.AssignmentNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.AssignmentService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.AssignmentForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.AssignmentDto;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentForm assignmentForm) {
        try {
            assignmentService.create(assignmentForm.toAssignmentDto());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssignment(@PathVariable Long id) {
        try {
            AssignmentDto assignmentDto = assignmentService.get(id);
            AssignmentForm assignmentForm = AssignmentForm.fromAssignmentDto(assignmentDto);
            return new ResponseEntity<>(assignmentForm, HttpStatus.OK);
        }
        catch (AssignmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAssignments() {
        try {
            List<AssignmentForm> assignments = assignmentService.getAll()
                    .stream()
                    .map(AssignmentForm::fromAssignmentDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(assignments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/lab{id}")
    public ResponseEntity<?> getLaboratoryClassAssignments(@PathVariable Long id) {
        try {
            List<AssignmentForm> assignments = assignmentService.getLaboratoryClassAssignments(id)
                    .stream()
                    .map(AssignmentForm::fromAssignmentDto)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(assignments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateAssignment(@PathVariable Long id, @RequestBody AssignmentForm assignmentForm) {
        try {
            LaboratoryClassDto laboratoryClassDto = null;
            if (assignmentForm.getLabId() != null || assignmentForm.getLabNumber() != null || assignmentForm.getLabTitle() != null) {
                laboratoryClassDto = LaboratoryClassDto.builder()
                        .id(assignmentForm.getLabId())
                        .title(assignmentForm.getLabTitle())
                        .labNumber(assignmentForm.getLabNumber())
                        .build();
            }

            AssignmentDto assignmentDto = assignmentForm.toAssignmentDto();
            assignmentDto.setLaboratoryClass(laboratoryClassDto);
            assignmentService.update(id, assignmentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AssignmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAssignments() {
        try {
            assignmentService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAssignment(@PathVariable Long id) {
        try {
            assignmentService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (AssignmentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
