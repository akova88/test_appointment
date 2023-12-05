package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.Role;
import com.cg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    User getByUsername(String username);

    User getByRole(Role role);
}
