package judgev2.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileViewModel {
    private String username;
    private Set<String> homework;
    private String email;
    private String githubAddress;

}
