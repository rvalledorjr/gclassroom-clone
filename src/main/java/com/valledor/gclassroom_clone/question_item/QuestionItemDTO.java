package com.valledor.gclassroom_clone.question_item;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionItemDTO {

    private Long id;

    @Size(max = 255)
    private String section;

    @NotNull
    private String question;

    @NotNull
    private Double assignPoints;

    private String feedBack;

    private Boolean validated;

    private Boolean preview;

    @NotNull
    private QuestionType questionType;

    private Long questionItems;

}
