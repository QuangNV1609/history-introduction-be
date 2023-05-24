package com.uet.quangnv.api;

import com.uet.quangnv.dto.QuestionDto;
import com.uet.quangnv.exception.domain.DataFormatWrong;
import com.uet.quangnv.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question")
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
public class QuestionApi {
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/get-question-for-exam")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<QuestionDto>> getQuestionForExam(
            @RequestParam("historicalPeriod") Integer historicalPeriod,
            @RequestParam("size") Integer size) {
        return new ResponseEntity<>(questionService.getQuestionForExam(historicalPeriod, size), HttpStatus.OK);
    }

    @GetMapping(value = "/search-question")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_2')")
    public ResponseEntity<List<QuestionDto>> searchQuestionDtos(
            @RequestParam(value = "historicalPeriod", required = false) Integer historicalPeriod,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "size", required = false) Integer size) {
        return new ResponseEntity<>(questionService.searchQuestionDto(historicalPeriod, status, size), HttpStatus.OK);
    }

    @PostMapping(value = "/save-question")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_2')")
    public ResponseEntity<QuestionDto> saveListQuestion(@RequestBody QuestionDto questionDto) throws DataFormatWrong {
        return new ResponseEntity<>(questionService.saveQuestionDto(questionDto), HttpStatus.OK);
    }

    @PostMapping(value = "/save-list-question")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN_2')")
    public ResponseEntity<List<QuestionDto>> saveListQuestion(@RequestBody List<QuestionDto> questionDtos) throws DataFormatWrong {
        return new ResponseEntity<>(questionService.saveListQuestionDto(questionDtos), HttpStatus.OK);
    }

    @PutMapping(value = "/censorship")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void censorshipQuestion(@RequestBody Long questionIds) {
        questionService.censorshipListQuestion(List.of(questionIds));
    }

    @PutMapping(value = "/censorship-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void censorshipListQuestion(@RequestBody List<Long> questionIds) {
        questionService.censorshipListQuestion(questionIds);
    }

    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteQuestion(@RequestBody Long questionIds) {
        questionService.censorshipListQuestion(List.of(questionIds));
    }

    @DeleteMapping(value = "/delete-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteListQuestion(@RequestBody List<Long> questionIds) {
        questionService.censorshipListQuestion(questionIds);
    }


}
