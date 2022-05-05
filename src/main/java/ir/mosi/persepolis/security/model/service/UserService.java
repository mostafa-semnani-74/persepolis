package ir.mosi.persepolis.security.model.service;

import ir.mosi.persepolis.security.model.domain.AppRole;
import ir.mosi.persepolis.security.model.domain.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);

    AppRole saveRole(AppRole role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();
}
