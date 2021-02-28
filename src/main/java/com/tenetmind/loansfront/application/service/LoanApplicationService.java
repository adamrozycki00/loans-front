package com.tenetmind.loansfront.application.service;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationClient client;

    @Autowired
    private LoanApplicationMapper mapper;

    public LoanApplicationDto getDto(Long id) {
        return client.getApplicationDto(id);
    }

    public List<LoanApplicationDto> getDtos() {
        return client.getApplicationDtos();
    }

    public List<LoanApplication> getAll() {
        return client.getApplicationDtos().stream()
                .map(LoanApplication::new)
                .collect(Collectors.toList());
    }

    public boolean save(LoanApplicationDto application) {
        return client.createApplication(application);
    }

    public boolean update(LoanApplicationDto application) {
        return client.updateApplication(application);
    }

    @Override
    public String toString() {
        return "LoanApplicationService{" +
                "client=" + client +
                ", mapper=" + mapper +
                '}';
    }

}
