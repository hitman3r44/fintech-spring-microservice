package com.wolverinesolution.lendingengine.domain.repository;

import com.wolverinesolution.lendingengine.domain.model.LoanApplication;
import com.wolverinesolution.lendingengine.domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    List<LoanApplication> findAllByStatusEquals(Status status);
}
