package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.SubmissionNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.SubmissionService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.SubmissionForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.SubmissionDto;
import com.gmail.filimon24.adelin.labactivitytracker.persistence.model.SubmissionId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<?> createSubmission(@RequestBody SubmissionForm submissionForm) {
        try {
        submissionService.create(submissionForm.toSubmissionDto());
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-submission")
    public ResponseEntity<?> createSubmissionByLoggedStudent(@RequestBody SubmissionForm submissionForm) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;

            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }

            submissionForm.setStudentUsername(username);
            submissionForm.setStudentId(null);
            submissionForm.setStudentEmail(null);

            submissionService.create(submissionForm.toSubmissionDto());
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getSubmissions() {
        try {
            List<SubmissionForm> submissions = submissionService.getAll()
                    .stream()
                    .map(SubmissionForm::fromSubmissionDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(submissions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{studentId}/{assignmentId}")
    public ResponseEntity<?> getSubmission(@PathVariable Long studentId, @PathVariable Long assignmentId) {
        try {
            SubmissionId submissionId = SubmissionId.builder()
                    .student(studentId)
                    .assignment(assignmentId)
                    .build();
            SubmissionDto submissionDto = submissionService.get(submissionId);
            SubmissionForm submissionForm = SubmissionForm.fromSubmissionDto(submissionDto);
            return new ResponseEntity<>(submissionForm, HttpStatus.OK);
        }
        catch (SubmissionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{studentId}/{assignmentId}")
    public ResponseEntity<?> updateSubmission(@PathVariable Long studentId, @PathVariable Long assignmentId, @RequestBody SubmissionForm submissionForm) {
        try {
            SubmissionId submissionId = SubmissionId.builder()
                    .assignment(assignmentId)
                    .student(studentId)
                    .build();

            SubmissionDto submissionDto = SubmissionDto.builder().grade(submissionForm.getGrade())
                    .build();

            submissionService.update(submissionId, submissionDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (SubmissionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSubmission() {
        try {
            submissionService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{studentId}/{assignmentId}")
    public ResponseEntity<?> deleteSubmission(@PathVariable Long studentId, @PathVariable Long assignmentId) {
        try {
            SubmissionId submissionId = SubmissionId.builder()
                    .student(studentId)
                    .assignment(assignmentId)
                    .build();

            submissionService.delete(submissionId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (SubmissionNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
