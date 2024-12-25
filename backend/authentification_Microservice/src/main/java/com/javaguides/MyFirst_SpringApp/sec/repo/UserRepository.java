package com.javaguides.MyFirst_SpringApp.sec.repo;
import com.javaguides.MyFirst_SpringApp.sec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail (String email);
}
