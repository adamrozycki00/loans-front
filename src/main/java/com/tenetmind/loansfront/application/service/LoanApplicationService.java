package com.tenetmind.loansfront.application.service;

import com.tenetmind.loansfront.application.client.LoanApplicationClient;
import com.tenetmind.loansfront.application.domainmodel.LoanApplication;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationDto;
import com.tenetmind.loansfront.application.domainmodel.LoanApplicationMapper;
import com.tenetmind.loansfront.currency.domainmodel.CurrencyDto;
import com.vaadin.flow.component.notification.Notification;
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
        if (application.getStatus().equals(NEW)) {
            if (application.getId() == null) {
                return client.createApplication(mapper.mapToDto(application));
            } else {
                return update(application);
            }
        }

        LoanApplicationDto applicationInDB = getDto(Long.parseLong(application.getId()));
        if (!application.getStatus().equals(applicationInDB.getStatus()))
            return update(application);

        return false;
    }

    public boolean update(LoanApplication application) {
        return client.updateApplication(mapper.mapToDto(application));
    }

    public boolean delete(LoanApplication application) {
        if (application.getStatus().equals(NEW)) {
            if (application.getId() != null) {
                return client.deleteApplication(Long.parseLong(application.getId()));
            }
        }
        Notification.show("You can only delete new applications.");
        return false;
    }

    @Override
    public String toString() {
        return "LoanApplicationService{" +
                "client=" + client +
                ", mapper=" + mapper +
                '}';
    }

}
