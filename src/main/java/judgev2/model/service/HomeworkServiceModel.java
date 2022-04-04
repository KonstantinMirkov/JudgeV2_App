package judgev2.model.service;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HomeworkServiceModel {
    private String id;
    private LocalDateTime addedOn;
    private String githubAddress;
    private UserServiceModel author;

}
