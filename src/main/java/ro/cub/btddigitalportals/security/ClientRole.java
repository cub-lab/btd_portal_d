package ro.cub.btddigitalportals.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "Client Access", code = ClientRole.CODE)
public interface ClientRole {
    String CODE = "client-access-role";
}
