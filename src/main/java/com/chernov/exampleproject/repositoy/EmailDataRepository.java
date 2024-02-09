package com.chernov.exampleproject.repositoy;

import com.chernov.exampleproject.model.entity.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

    boolean existsByEmail(String email);

    void deleteByIdAndUserId(Long emailId, Long userId);
}
