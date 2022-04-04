package judgev2.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentServiceModel {

    private Integer score;
    private String textContent;
    private UserServiceModel author;

}
