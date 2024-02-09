package com.chernov.exampleproject.repositoy;

import com.chernov.exampleproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u "
            + "LEFT JOIN u.phoneData "
            + "LEFT JOIN u.emailData "
            + "WHERE u.id = :id "
    )
    Optional<User> findEagerBy(@Param("id") Long id);

    @Query("SELECT u FROM User u "
            + "JOIN FETCH u.emailData ed "
            + "WHERE ed.email = :email ")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT COUNT(pd) FROM User u "
            + "JOIN u.phoneData pd "
            + "WHERE u.id = :id "
    )
    int getPhoneCount(@Param("id") Long id);

    @Query("SELECT COUNT(ed) FROM User u "
            + "JOIN u.emailData ed "
            + "WHERE u.id = :id "
    )
    int getEmailCount(@Param("id") Long id);
}
