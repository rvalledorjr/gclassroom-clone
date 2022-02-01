package com.valledor.gclassroom_clone.assessment_form;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AssessmentFormService {

    private final AssessmentFormRepository assessmentFormRepository;

    public AssessmentFormService(final AssessmentFormRepository assessmentFormRepository) {
        this.assessmentFormRepository = assessmentFormRepository;
    }

    public List<AssessmentFormDTO> findAll() {
        return assessmentFormRepository.findAll()
                .stream()
                .map(assessmentForm -> mapToDTO(assessmentForm, new AssessmentFormDTO()))
                .collect(Collectors.toList());
    }

    public AssessmentFormDTO get(final Long id) {
        return assessmentFormRepository.findById(id)
                .map(assessmentForm -> mapToDTO(assessmentForm, new AssessmentFormDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final AssessmentFormDTO assessmentFormDTO) {
        final AssessmentForm assessmentForm = new AssessmentForm();
        mapToEntity(assessmentFormDTO, assessmentForm);
        return assessmentFormRepository.save(assessmentForm).getId();
    }

    public void update(final Long id, final AssessmentFormDTO assessmentFormDTO) {
        final AssessmentForm assessmentForm = assessmentFormRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(assessmentFormDTO, assessmentForm);
        assessmentFormRepository.save(assessmentForm);
    }

    public void delete(final Long id) {
        assessmentFormRepository.deleteById(id);
    }

    private AssessmentFormDTO mapToDTO(final AssessmentForm assessmentForm,
            final AssessmentFormDTO assessmentFormDTO) {
        assessmentFormDTO.setId(assessmentForm.getId());
        assessmentFormDTO.setTitle(assessmentForm.getTitle());
        assessmentFormDTO.setDescription(assessmentForm.getDescription());
        return assessmentFormDTO;
    }

    private AssessmentForm mapToEntity(final AssessmentFormDTO assessmentFormDTO,
            final AssessmentForm assessmentForm) {
        assessmentForm.setTitle(assessmentFormDTO.getTitle());
        assessmentForm.setDescription(assessmentFormDTO.getDescription());
        return assessmentForm;
    }

}
