package com.valledor.gclassroom_clone.question_item;

import com.valledor.gclassroom_clone.assessment_form.AssessmentForm;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class QuestionItem {

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

    @Column
    private String section;

    @Column(nullable = false, columnDefinition = "clob")
    private String question;

    @Column(nullable = false)
    private Double assignPoints;

    @Column(columnDefinition = "clob")
    private String feedBack;

    @Column
    private Boolean validated;

    @Column
    private Boolean preview;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_items_id")
    private AssessmentForm questionItems;

}
