package com.security.auth;

import java.util.Optional;

/*
* Makes it easy for having many repositories.
* Maybe have different repositories for different postgres,mysql etc.
* */
public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername
            (String username);
}
