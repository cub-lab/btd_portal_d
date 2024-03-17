package ro.cub.btddigitalportals.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "Anonymous Access", code = AnonymousRole.CODE)
public interface AnonymousRole {
    String CODE = "anonymous-access-role";

}
