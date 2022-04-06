package hu.webuni.hr.hegetomi.service.employee;

import hu.webuni.hr.hegetomi.model.Employee;

import java.time.LocalDateTime;
import java.util.*;

public abstract class EmployeeServiceAncestor {

    private final Map<Long, Employee> employees = new HashMap<>();

    {
        employees.put(0L, new Employee(0, "John Doe", "Architect", 2000, LocalDateTime.of(2000, 2, 10, 12, 12)));
        employees.put(1L, new Employee(1, "Jane Doe", "Lead dev", 1500, LocalDateTime.of(2010, 4, 15, 12, 12)));
        employees.put(2L, new Employee(2, "Joe Schmoe", "Senior dev", 1000, LocalDateTime.of(2015, 6, 20, 12, 12)));
        employees.put(3L, new Employee(3, "Jane Smith", "Medior dev", 500, LocalDateTime.of(2020, 8, 25, 12, 12)));
    }


    public Optional<Employee> save(Employee employee){
        if (!employees.containsKey(employee.getId())) {
            employees.put(employee.getId(), employee);
            return Optional.of(employee);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Employee> edit(Employee employee, long id){
        if (!employees.containsKey(employee.getId())) {
            return Optional.empty();
        } else {
            employee.setId(id);
            employees.put(employee.getId(), employee);
            return Optional.of(employee);
        }
    }



    public List<Employee> findAll(){
        return new ArrayList<>(employees.values());
    }

    public Optional<Employee> findById(long id){
        return Optional.ofNullable(employees.get(id));
    }

    public void delete(long id){
        employees.remove(id);
    }

}
