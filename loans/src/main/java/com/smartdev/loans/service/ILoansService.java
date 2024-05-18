package com.smartdev.loans.service;

import com.smartdev.loans.dto.LoansDto;

import java.util.List;

public interface ILoansService {
    void createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    List<LoansDto> fetchLoans();

    boolean updateLoan(LoansDto loansDto);

    boolean deleteLoan(String mobileNumber);

}
