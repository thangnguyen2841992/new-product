package com.regain.product.repository;

import com.regain.product.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {
}
