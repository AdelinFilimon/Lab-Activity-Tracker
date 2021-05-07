package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TokenNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.TokenService;
import com.gmail.filimon24.adelin.labactivitytracker.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> createToken(StudentDto studentDto) {
        try {
            tokenService.create(studentDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTokens() {
        try {
            return new ResponseEntity<>(tokenService.getAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getToken(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(tokenService.get(id), HttpStatus.OK);
        }
        catch (TokenNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTokens() {
        try {
            tokenService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToken(@PathVariable Long id) {
        try {
            tokenService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (TokenNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
