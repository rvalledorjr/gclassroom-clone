package com.valledor.gclassroom_clone.question_item;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/questionItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionItemController {

    private final QuestionItemService questionItemService;

    public QuestionItemController(final QuestionItemService questionItemService) {
        this.questionItemService = questionItemService;
    }

    @GetMapping
    public ResponseEntity<List<QuestionItemDTO>> getAllQuestionItems() {
        return ResponseEntity.ok(questionItemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionItemDTO> getQuestionItem(@PathVariable final Long id) {
        return ResponseEntity.ok(questionItemService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createQuestionItem(
            @RequestBody @Valid final QuestionItemDTO questionItemDTO) {
        return new ResponseEntity<>(questionItemService.create(questionItemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateQuestionItem(@PathVariable final Long id,
            @RequestBody @Valid final QuestionItemDTO questionItemDTO) {
        questionItemService.update(id, questionItemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionItem(@PathVariable final Long id) {
        questionItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
