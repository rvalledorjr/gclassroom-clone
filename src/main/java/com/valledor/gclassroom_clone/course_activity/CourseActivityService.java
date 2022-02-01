package com.valledor.gclassroom_clone.course_activity;

import com.valledor.gclassroom_clone.assessment_form.AssessmentForm;
import com.valledor.gclassroom_clone.assessment_form.AssessmentFormRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CourseActivityService {

    private final CourseActivityRepository courseActivityRepository;
    private final AssessmentFormRepository assessmentFormRepository;

    public CourseActivityService(final CourseActivityRepository courseActivityRepository,
            final AssessmentFormRepository assessmentFormRepository) {
        this.courseActivityRepository = courseActivityRepository;
        this.assessmentFormRepository = assessmentFormRepository;
    }

    public List<CourseActivityDTO> findAll() {
        return courseActivityRepository.findAll()
                .stream()
                .map(courseActivity -> mapToDTO(courseActivity, new CourseActivityDTO()))
                .collect(Collectors.toList());
    }

    public CourseActivityDTO get(final Long id) {
        return courseActivityRepository.findById(id)
                .map(courseActivity -> mapToDTO(courseActivity, new CourseActivityDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CourseActivityDTO courseActivityDTO) {
        final CourseActivity courseActivity = new CourseActivity();
        mapToEntity(courseActivityDTO, courseActivity);
        return courseActivityRepository.save(courseActivity).getId();
    }

    public void update(final Long id, final CourseActivityDTO courseActivityDTO) {
        final CourseActivity courseActivity = courseActivityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(courseActivityDTO, courseActivity);
        courseActivityRepository.save(courseActivity);
    }

    public void delete(final Long id) {
        courseActivityRepository.deleteById(id);
    }

    private CourseActivityDTO mapToDTO(final CourseActivity courseActivity,
            final CourseActivityDTO courseActivityDTO) {
        courseActivityDTO.setId(courseActivity.getId());
        courseActivityDTO.setName(courseActivity.getName());
        courseActivityDTO.setDescription(courseActivity.getDescription());
        courseActivityDTO.setDatePosted(courseActivity.getDatePosted());
        courseActivityDTO.setDateDue(courseActivity.getDateDue());
        courseActivityDTO.setGraded(courseActivity.getGraded());
        courseActivityDTO.setTurnedIn(courseActivity.getTurnedIn());
        courseActivityDTO.setTotalPoints(courseActivity.getTotalPoints());
        courseActivityDTO.setActualPoints(courseActivity.getActualPoints());
        courseActivityDTO.setAssessmentForm(courseActivity.getAssessmentForm() == null ? null : courseActivity.getAssessmentForm().getId());
        return courseActivityDTO;
    }

    private CourseActivity mapToEntity(final CourseActivityDTO courseActivityDTO,
            final CourseActivity courseActivity) {
        courseActivity.setName(courseActivityDTO.getName());
        courseActivity.setDescription(courseActivityDTO.getDescription());
        courseActivity.setDatePosted(courseActivityDTO.getDatePosted());
        courseActivity.setDateDue(courseActivityDTO.getDateDue());
        courseActivity.setGraded(courseActivityDTO.getGraded());
        courseActivity.setTurnedIn(courseActivityDTO.getTurnedIn());
        courseActivity.setTotalPoints(courseActivityDTO.getTotalPoints());
        courseActivity.setActualPoints(courseActivityDTO.getActualPoints());
        if (courseActivityDTO.getAssessmentForm() != null && (courseActivity.getAssessmentForm() == null || !courseActivity.getAssessmentForm().getId().equals(courseActivityDTO.getAssessmentForm()))) {
            final AssessmentForm assessmentForm = assessmentFormRepository.findById(courseActivityDTO.getAssessmentForm())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "assessmentForm not found"));
            courseActivity.setAssessmentForm(assessmentForm);
        }
        return courseActivity;
    }

}
