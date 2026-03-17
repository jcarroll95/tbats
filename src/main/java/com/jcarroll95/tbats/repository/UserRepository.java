package com.jcarroll95.tbats.repository;
import com.jcarroll95.tbats.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID>{

    Optional<User> findByUsername(String username);

}
