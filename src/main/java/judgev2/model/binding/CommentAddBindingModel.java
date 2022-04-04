package judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CommentAddBindingModel {

    @Min(value = 2, message = "The score must be bigger than 2")
    @Max(value = 6, message = "THe score must be less than or equal to 6")
    private Integer score;

    @Length(min = 3, message = "The description must be more than 3 characters long")
    @NotBlank
    private String textContent;

    private String homeworkId;
}
