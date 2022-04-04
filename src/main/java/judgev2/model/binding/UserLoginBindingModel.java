package judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {
    @NotBlank(message = "Must not be blank")
    @Length(min = 2, message = "The username must be at least 2 characters long")
    private String username;

    @NotBlank(message = "Must not be blank")
    @Length(min = 3, message = "The password must be at least 3 characters long")
    private String password;
}
