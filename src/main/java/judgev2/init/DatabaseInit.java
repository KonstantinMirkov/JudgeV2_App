package judgev2.init;

import judgev2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {
    private final RoleService roleService;

    @Autowired
    public DatabaseInit(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
    }
}
