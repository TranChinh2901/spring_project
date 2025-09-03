package com.example.spring_project.repository;

import com.example.spring_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByNameContainingIgnoreCase(String name);

    List<User> findByCompanyId(Long companyId);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:name% AND u.email LIKE %:email%")
    List<User> findByNameAndEmailContaining(@Param("name") String name, @Param("email") String email);
}
