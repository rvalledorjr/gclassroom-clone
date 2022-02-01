package com.valledor.gclassroom_clone.assessment_form;

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
@RequestMapping(value = "/api/assessmentForms", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssessmentFormController {

    private final AssessmentFormService assessmentFormService;

    public AssessmentFormController(final AssessmentFormService assessmentFormService) {
        this.assessmentFormService = assessmentFormService;
    }

    @GetMapping
    public ResponseEntity<List<AssessmentFormDTO>> getAllAssessmentForms() {
        return ResponseEntity.ok(assessmentFormService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentFormDTO> getAssessmentForm(@PathVariable final Long id) {
        return ResponseEntity.ok(assessmentFormService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createAssessmentForm(
            @RequestBody @Valid final AssessmentFormDTO assessmentFormDTO) {
        return new ResponseEntity<>(assessmentFormService.create(assessmentFormDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAssessmentForm(@PathVariable final Long id,
            @RequestBody @Valid final AssessmentFormDTO assessmentFormDTO) {
        assessmentFormService.update(id, assessmentFormDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessmentForm(@PathVariable final Long id) {
        assessmentFormService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
