package com.tenetmind.loansfront.application.service;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanApplicationService {

    private final String NEW = "New";
    private final String ACCEPTED = "Accepted";
    private final String CANCELED = "Canceled";
    private final String DENIED = "Denied";

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

    public boolean save(LoanApplicationDto applicationDto) {
        return client.createApplication(applicationDto);
    }

    public boolean save(LoanApplication application) {
        if (application.getStatus().equals(NEW))
            return client.createApplication(mapper.mapToDto(application));
        return false;
    }

    public boolean update(LoanApplicationDto application) {
        return client.updateApplication(application);
    }

    public boolean delete(Long id) {
        return client.deleteApplication(id);
    }

    public boolean delete(LoanApplication application) {
        return client.deleteApplication(Long.parseLong(application.getId()));
    }

    @Override
    public String toString() {
        return "LoanApplicationService{" +
                "client=" + client +
                ", mapper=" + mapper +
                '}';
    }

}
