package com.valledor.gclassroom_clone.course_activity;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseActivityDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String description;

    @NotNull
    private LocalDateTime datePosted;

    private LocalDateTime dateDue;

    private Boolean graded;

    private Boolean turnedIn;

    private Double totalPoints;

    private Double actualPoints;

    private Long assessmentForm;

}
