package judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseAddBindingModel {
    @Length(min = 2, message = "The name must be at least 2 characters")
    @NotBlank(message = "The name cannot be blank")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @PastOrPresent(message = "The date cannot be in the future")
    @NotNull(message = "The date cannot have null as a value")
    private LocalDateTime startedOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "The date cannot be in the past")
    @NotNull(message = "The date cannot have null as a value")
    private LocalDateTime dueDate;
}
