package com.rishabh.lendingengine.application;

import com.rishabh.lendingengine.application.model.LoanRepaymentRequest;
import com.rishabh.lendingengine.application.model.LoanRequest;
import com.rishabh.lendingengine.application.service.TokenValidationService;
import com.rishabh.lendingengine.application.service.impl.TokenValidationServiceImpl;
import com.rishabh.lendingengine.domain.model.Loan;
import com.rishabh.lendingengine.domain.model.LoanApplication;
import com.rishabh.lendingengine.domain.model.Status;
import com.rishabh.lendingengine.domain.model.User;
import com.rishabh.lendingengine.domain.repository.LoanApplicationRepository;
import com.rishabh.lendingengine.domain.service.LoanApplicationAdapter;
import com.rishabh.lendingengine.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanController {

    private final LoanApplicationRepository loanApplicationRepository;
    private final LoanApplicationAdapter loanApplicationAdapter;
    private final LoanService loanService;
    private final TokenValidationService tokenValidationService;

    @Autowired
    public LoanController(LoanApplicationRepository loanApplicationRepository,LoanApplicationAdapter loanApplicationAdapter, LoanService loanService, TokenValidationService tokenValidationService) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.loanApplicationAdapter = loanApplicationAdapter;
        this.loanService = loanService;
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping(value= "/loan/request")
    public void requestLoan(@RequestBody LoanRequest lonaRequest, HttpServletRequest request){
        User borrower=tokenValidationService.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanApplicationRepository.save(loanApplicationAdapter.transform(lonaRequest,borrower));
    }

    @GetMapping(value = "/loan/requests")
    public List<LoanApplication> getAllRequests(HttpServletRequest request){
        tokenValidationService.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanApplicationRepository.findAllByStatusEquals(Status.ONGOING);
    }

    @GetMapping(value="/loan/{status}/borrowed")
    public List<Loan> findBorrowedLoans(@RequestHeader String authorization,@PathVariable Status status){
        User borrower = tokenValidationService.validateToken(authorization);
        return loanService.findBorrowedLoans(borrower,status);
    }

    @GetMapping(value = "/loan/{status}/lent")
    public List<Loan> findLentLoans(@RequestHeader String authorization,@PathVariable Status status){
        User lender = tokenValidationService.validateToken(authorization);
        return loanService.findLentLoans(lender,status);
    }

    @PostMapping(value = "/loan/repay")
    public void replayLoan(@RequestBody LoanRepaymentRequest loanRepaymentRequest,@RequestHeader String authorization){
        User borrower = tokenValidationService.validateToken(authorization);
        loanService.repayLoan(loanRepaymentRequest.getAmount(),loanRepaymentRequest.getLoanId(),borrower);
    }

    @PostMapping(value= "/loan/accept/{loanApplicationId}")
    public void requestLoan(@PathVariable final String loanApplicationId,HttpServletRequest request){
        User username =tokenValidationService.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(Long.parseLong(loanApplicationId),username.getUsername());
    }

    @GetMapping(value = "/loans")
    public List<Loan> getAllLoans(HttpServletRequest request){
        tokenValidationService.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanService.getAcceptedLoans();
    }

    @GetMapping(value = "/loans/{loanId}")
    public Loan getLoan(@PathVariable final String loanId,HttpServletRequest request){
        tokenValidationService.validateToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanService.getLoanById(Long.parseLong(loanId));
    }

}
