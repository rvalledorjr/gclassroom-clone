package com.valledor.gclassroom_clone.course_activity;

import com.valledor.gclassroom_clone.assessment_form.AssessmentForm;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class CourseActivity {

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
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private LocalDateTime datePosted;

    @Column
    private LocalDateTime dateDue;

    @Column
    private Boolean graded;

    @Column
    private Boolean turnedIn;

    @Column
    private Double totalPoints;

    @Column
    private Double actualPoints;

    @OneToOne
    @JoinColumn(name = "assessment_form_id")
    private AssessmentForm assessmentForm;

}
