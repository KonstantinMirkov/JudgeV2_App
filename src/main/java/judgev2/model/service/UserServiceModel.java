package judgev2.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel extends BaseServiceModel {
    @NotBlank(message = "Must not be blank")
    @Length(min = 2, message = "The username must be at least 2 characters long")
    private String username;

    @NotBlank(message = "Must not be blank")
    @Length(min = 3, message = "The password must be at least 3 characters long")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Must not be blank")
    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "https://github.com/\\w+/", message = "Enter a valid github address")
    @NotBlank(message = "Must not be blank")
    private String githubAddress;

    private RoleServiceModel role;

}
