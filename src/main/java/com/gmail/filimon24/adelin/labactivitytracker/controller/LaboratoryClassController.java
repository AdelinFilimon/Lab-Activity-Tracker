package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.LaboratoryClassNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.LaboratoryClassService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.LaboratoryClassForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.LaboratoryClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("laboratory-classes")
@RequiredArgsConstructor
public class LaboratoryClassController {

    private final LaboratoryClassService laboratoryClassService;

    @PostMapping
    public ResponseEntity<?> createLaboratoryClass(@RequestBody LaboratoryClassForm laboratoryClassF) {
        try {
            laboratoryClassService.create(laboratoryClassF.toLaboratoryClassDto());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(value = {"/all"})
    public ResponseEntity<?> getLaboratoryClasses() {
        List<LaboratoryClassForm> laboratoryClasses = laboratoryClassService.getAll()
                .stream()
                .map(LaboratoryClassForm::fromLaboratoryClassDto)
                .collect(Collectors.toList());
        try {
            return new ResponseEntity<>(laboratoryClasses, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLaboratoryClass(@PathVariable Long id) {
        LaboratoryClassDto laboratoryClassDto = laboratoryClassService.get(id);
        LaboratoryClassForm laboratoryClass = LaboratoryClassForm.fromLaboratoryClassDto(laboratoryClassDto);
        try {
            return new ResponseEntity<>(laboratoryClass, HttpStatus.OK);
        }
        catch (LaboratoryClassNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteLaboratoryClasses() {
        try {
            laboratoryClassService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaboratoryClass(@PathVariable Long id) {
        try {
            laboratoryClassService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (LaboratoryClassNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateLaboratoryClass(@PathVariable Long id, @RequestBody LaboratoryClassForm laboratoryClassF) {
        try {
            laboratoryClassService.update(id, laboratoryClassF.toLaboratoryClassDto());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (LaboratoryClassNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
