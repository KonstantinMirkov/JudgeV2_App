package judgev2.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "homework")
@Getter
@Setter
@NoArgsConstructor
public class HomeworkEntity extends BaseEntity {

    @Column(name = "added_on", nullable = false)
    private LocalDateTime addedOn;

    @Column(name = "github_address", nullable = false, unique = true)
    private String githubAddress;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private ExerciseEntity exerciseEntity;

    @OneToMany(mappedBy = "homework", fetch = FetchType.EAGER)
    private Set<CommentEntity> commentEntitySet;
}
