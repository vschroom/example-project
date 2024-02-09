package com.chernov.exampleproject.repositoy;

import com.chernov.exampleproject.model.entity.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

    boolean existsByPhone(String phone);

    void deleteByIdAndUserId(Long phoneId, Long userId);
}
