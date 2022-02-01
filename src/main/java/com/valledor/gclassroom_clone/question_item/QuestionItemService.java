package com.valledor.gclassroom_clone.question_item;

import com.valledor.gclassroom_clone.assessment_form.AssessmentForm;
import com.valledor.gclassroom_clone.assessment_form.AssessmentFormRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class QuestionItemService {

    private final QuestionItemRepository questionItemRepository;
    private final AssessmentFormRepository assessmentFormRepository;

    public QuestionItemService(final QuestionItemRepository questionItemRepository,
            final AssessmentFormRepository assessmentFormRepository) {
        this.questionItemRepository = questionItemRepository;
        this.assessmentFormRepository = assessmentFormRepository;
    }

    public List<QuestionItemDTO> findAll() {
        return questionItemRepository.findAll()
                .stream()
                .map(questionItem -> mapToDTO(questionItem, new QuestionItemDTO()))
                .collect(Collectors.toList());
    }

    public QuestionItemDTO get(final Long id) {
        return questionItemRepository.findById(id)
                .map(questionItem -> mapToDTO(questionItem, new QuestionItemDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final QuestionItemDTO questionItemDTO) {
        final QuestionItem questionItem = new QuestionItem();
        mapToEntity(questionItemDTO, questionItem);
        return questionItemRepository.save(questionItem).getId();
    }

    public void update(final Long id, final QuestionItemDTO questionItemDTO) {
        final QuestionItem questionItem = questionItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(questionItemDTO, questionItem);
        questionItemRepository.save(questionItem);
    }

    public void delete(final Long id) {
        questionItemRepository.deleteById(id);
    }

    private QuestionItemDTO mapToDTO(final QuestionItem questionItem,
            final QuestionItemDTO questionItemDTO) {
        questionItemDTO.setId(questionItem.getId());
        questionItemDTO.setSection(questionItem.getSection());
        questionItemDTO.setQuestion(questionItem.getQuestion());
        questionItemDTO.setAssignPoints(questionItem.getAssignPoints());
        questionItemDTO.setFeedBack(questionItem.getFeedBack());
        questionItemDTO.setValidated(questionItem.getValidated());
        questionItemDTO.setPreview(questionItem.getPreview());
        questionItemDTO.setQuestionType(questionItem.getQuestionType());
        questionItemDTO.setQuestionItems(questionItem.getQuestionItems() == null ? null : questionItem.getQuestionItems().getId());
        return questionItemDTO;
    }

    private QuestionItem mapToEntity(final QuestionItemDTO questionItemDTO,
            final QuestionItem questionItem) {
        questionItem.setSection(questionItemDTO.getSection());
        questionItem.setQuestion(questionItemDTO.getQuestion());
        questionItem.setAssignPoints(questionItemDTO.getAssignPoints());
        questionItem.setFeedBack(questionItemDTO.getFeedBack());
        questionItem.setValidated(questionItemDTO.getValidated());
        questionItem.setPreview(questionItemDTO.getPreview());
        questionItem.setQuestionType(questionItemDTO.getQuestionType());
        if (questionItemDTO.getQuestionItems() != null && (questionItem.getQuestionItems() == null || !questionItem.getQuestionItems().getId().equals(questionItemDTO.getQuestionItems()))) {
            final AssessmentForm questionItems = assessmentFormRepository.findById(questionItemDTO.getQuestionItems())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "questionItems not found"));
            questionItem.setQuestionItems(questionItems);
        }
        return questionItem;
    }

}
