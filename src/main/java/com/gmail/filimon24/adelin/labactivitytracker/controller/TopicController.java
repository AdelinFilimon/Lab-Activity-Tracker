package com.gmail.filimon24.adelin.labactivitytracker.controller;

import com.gmail.filimon24.adelin.labactivitytracker.business.exception.InvalidFieldException;
import com.gmail.filimon24.adelin.labactivitytracker.business.exception.TopicNotFoundException;
import com.gmail.filimon24.adelin.labactivitytracker.business.service.TopicService;
import com.gmail.filimon24.adelin.labactivitytracker.controller.DataTransferUtil.TopicForm;
import com.gmail.filimon24.adelin.labactivitytracker.model.TopicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopic(@PathVariable Long id) {
        try {
            TopicDto topicDto = topicService.get(id);
            TopicForm topicForm = TopicForm.fromTopicDto(topicDto);
            return new ResponseEntity<>(topicForm, HttpStatus.OK);
        }
        catch (TopicNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getTopics() {
        try {
            List<TopicForm> topicForms = topicService.getAll()
                    .stream()
                    .map(TopicForm::fromTopicDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(topicForms, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicForm topicForm) {
        try {
            TopicDto topicDto = topicForm.toTopicDto();
            topicService.create(topicDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id, @RequestBody TopicForm topicForm) {
        try {
            TopicDto topicDto = topicForm.toTopicDto();
            topicService.update(id, topicDto);
            return new ResponseEntity<> (HttpStatus.OK);
        }
        catch (TopicNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (InvalidFieldException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {
        try {
            topicService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (TopicNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTopics() {
        try {
            topicService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
