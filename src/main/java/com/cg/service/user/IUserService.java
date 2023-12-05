package com.cg.service.user;

import com.cg.model.Customer;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends IGeneralService<User, Long>, UserDetailsService {

    Boolean existsByUsername(String username);

    User getByUsername(String username);
    User getByRole(Role role);
    void softDelete(User user);

}