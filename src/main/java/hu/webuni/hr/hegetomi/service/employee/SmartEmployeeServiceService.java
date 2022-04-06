package hu.webuni.hr.hegetomi.service.employee;

import hu.webuni.hr.hegetomi.config.HrConfigProperties;
import hu.webuni.hr.hegetomi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class SmartEmployeeServiceService extends EmployeeServiceAncestor implements EmployeeService {

    @Autowired
    HrConfigProperties config;

    @Override
    public int getPayRaisePercent(Employee employee) {
        checkConfigIntegrity();
        long months = getMonths(employee);

        for (int i = 0; i < config.getRaise().getSmart().getSince().size(); i++) {
            if (months >= config.getRaise().getSmart().getSince().get(i) * 12) {
                return config.getRaise().getSmart().getPercent().get(i);
            }
        }
        return 0;
    }

    private void checkConfigIntegrity() {
        int limitsSize = config.getRaise().getSmart().getSince().size();
        int percentsSize = config.getRaise().getSmart().getPercent().size();
        if (limitsSize > percentsSize) {
            for (int i = percentsSize; i <= limitsSize; i++) {
                config.getRaise().getSmart().getPercent().add(0);
            }
        }
    }

    private long getMonths(Employee employee) {
        LocalDateTime currDate = LocalDateTime.now();
        return ChronoUnit.MONTHS.between(employee.getEmpSince(), currDate);
    }

}
