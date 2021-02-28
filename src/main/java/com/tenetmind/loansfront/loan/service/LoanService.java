package com.tenetmind.loansfront.loan.service;

import com.tenetmind.loansfront.loan.client.LoanClient;
import com.tenetmind.loansfront.loan.domainmodel.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanClient client;

    public LoanDto get(Long id) {
        return client.getLoanDto(id);
    }

    public List<LoanDto> getAll() {
        return client.getLoanDtos();
    }

    public boolean save(LoanDto application) {
        return client.createLoan(application);
    }

    public boolean update(LoanDto application) {
        return client.updateLoan(application);
    }

}
