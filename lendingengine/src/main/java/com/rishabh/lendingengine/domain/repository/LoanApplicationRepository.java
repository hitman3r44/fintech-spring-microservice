package com.rishabh.lendingengine.domain.repository;

import com.rishabh.lendingengine.application.model.LoanRequest;
import com.rishabh.lendingengine.domain.model.LoanApplication;
import com.rishabh.lendingengine.domain.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {

    List<LoanApplication> findAllByStatusEquals(Status status);
}
