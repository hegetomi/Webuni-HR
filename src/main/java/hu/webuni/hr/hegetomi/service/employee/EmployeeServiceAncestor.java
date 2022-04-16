package hu.webuni.hr.hegetomi.service.employee;

import hu.webuni.hr.hegetomi.model.company.CompanyPositionSalary;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.model.Position;
import hu.webuni.hr.hegetomi.repository.company.CompanyPositionSalaryRepository;
import hu.webuni.hr.hegetomi.repository.EmployeeRepository;
import hu.webuni.hr.hegetomi.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class EmployeeServiceAncestor {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CompanyPositionSalaryRepository companyPositionSalaryRepository;

    @Transactional
    public Employee save(Employee employee) {
        cascadePosition(employee);
        return employeeRepository.save(employee);

    }

    @Transactional
    public Optional<Employee> edit(Employee employee, long id) {
        if (employeeRepository.existsById(employee.getId())) {
            Employee databaseEntity = employeeRepository.getById(id);
            cascadePosition(employee);
            employee.setWorksAt(databaseEntity.getWorksAt());
            if(databaseEntity.getWorksAt()!=null){
                companyPositionSalaryRepository.save(new CompanyPositionSalary(0,databaseEntity.getWorksAt(),employee.getTitle(),1));
            }
            return Optional.of(employeeRepository.save(employee));
        }
        return Optional.empty();
    }
//Built-in cascading does not work at all
    private void cascadePosition(Employee employee) {
        Optional<Position> position = positionRepository.getByName(employee.getTitle().getName());
        if(position.isEmpty()) {
            positionRepository.save(employee.getTitle());
        } else {
            employee.setTitle(position.get());
        }
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public void delete(long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> greaterSalaryThan(long amount) {
        return employeeRepository.findBySalaryGreaterThan(amount);
    }

    public Page<Employee> findByName(String name,int page, int size) {
        return employeeRepository.findByNameContaining(name, PageRequest.of(page,size));
    }

    public List<Employee> findByTitle(String title) {
        return employeeRepository.findByTitle(title);
    }

    public List<Employee> findByEmpSinceBetween(LocalDateTime d1, LocalDateTime d2) {
        return employeeRepository.findByEmpSinceBetween(d1, d2);
    }

}
