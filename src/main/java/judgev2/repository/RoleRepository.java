package judgev2.repository;

import judgev2.model.entity.RoleEntity;
import judgev2.model.entity.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByName(RoleName name);
}
