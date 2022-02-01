package com.valledor.gclassroom_clone.assessment_form;

import com.valledor.gclassroom_clone.course_activity.CourseActivity;
import com.valledor.gclassroom_clone.question_item.QuestionItem;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class AssessmentForm {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @OneToOne(mappedBy = "assessmentForm", fetch = FetchType.LAZY)
    private CourseActivity assessmentForm;

    @OneToMany(mappedBy = "questionItems")
    private Set<QuestionItem> questionItemsQuestionItems;

}
