package ir.mosi.persepolis.security.api.payload;

import lombok.Data;

@Data
public class RoleToUserForm {
    private String username;
    private String roleName;
}
