package judgev2.service;

import judgev2.model.entity.enumeration.RoleName;
import judgev2.model.service.RoleServiceModel;

public interface RoleService {
    void initRoles();
    RoleServiceModel findByName(RoleName name);
}
