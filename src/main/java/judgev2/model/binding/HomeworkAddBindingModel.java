package judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
public class HomeworkAddBindingModel {
    private String exercise;

    @Pattern(regexp = "https://github.com/\\w+/\\w+/")
    private String githubAddress;
}
