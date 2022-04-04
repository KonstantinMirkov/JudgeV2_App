package judgev2.model.service;

import judgev2.model.entity.enumeration.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleServiceModel extends BaseServiceModel {
    private RoleName name;
}
