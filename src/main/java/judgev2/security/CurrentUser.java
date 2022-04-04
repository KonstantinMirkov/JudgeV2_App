package judgev2.security;

import judgev2.model.entity.enumeration.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@NoArgsConstructor
@Component
@SessionScope
public class CurrentUser {
    private String id;
    private String username;
    private RoleName role;

    public CurrentUser setId(String id) {
        this.id = id;
        return this;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public CurrentUser setRole(RoleName role) {
        this.role = role;
        return this;
    }

    public Boolean isAnonymous() {
        return this.getUsername() == null;
    }

    public Boolean isAdmin() {
        return this.getRole().equals(RoleName.ADMIN);
    }

    public void clear() {
        this.setId(null);
        this.setUsername(null);
        this.setRole(null);
    }
}
