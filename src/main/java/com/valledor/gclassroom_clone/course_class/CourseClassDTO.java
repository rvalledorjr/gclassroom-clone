package com.valledor.gclassroom_clone.course_class;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseClassDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String section;

    @NotNull
    @Size(max = 255)
    private String subject;

    @Size(max = 255)
    private String room;

    @NotNull
    @Size(max = 255)
    private String bannerImage;

    private Boolean archived;

    @NotNull
    private Long teacher;

}
