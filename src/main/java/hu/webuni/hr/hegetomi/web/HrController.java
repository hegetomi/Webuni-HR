package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.mapper.EmployeeMapper;
import hu.webuni.hr.hegetomi.model.Employee;
import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class HrController {


    @Autowired
    EmployeeServiceAncestor employeeServiceAncestor;


    @Autowired
    EmployeeMapper hrEmployeeMapper;

    @GetMapping
    public List<EmployeeDto> getAll() {
        return hrEmployeeMapper.employeesToDtos(employeeServiceAncestor.findAll());
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable long id) {
        return hrEmployeeMapper.employeeToDto(employeeServiceAncestor.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto dto) {
        return hrEmployeeMapper.employeeToDto(employeeServiceAncestor.save(hrEmployeeMapper.dtoToEmployee(dto)));
    }

    @PutMapping("/{id}")
    public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto dto) {
        dto.setId(id);
        return hrEmployeeMapper.employeeToDto(employeeServiceAncestor.edit(hrEmployeeMapper.dtoToEmployee(dto), id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeServiceAncestor.delete(id);
    }

    //eg.:/api/employees/?salary=1000
    @GetMapping(value = "/", params = "salary")
    public List<EmployeeDto> getSalaryGreaterThan(@RequestParam("salary") long amount) {
        return hrEmployeeMapper.employeesToDtos(employeeServiceAncestor.greaterSalaryThan(amount));
    }

    @GetMapping(value = "/", params = "title")
    public List<EmployeeDto> getByTitle(@RequestParam("title") String title) {
        return hrEmployeeMapper.employeesToDtos(employeeServiceAncestor.findByTitle(title));
    }

    @GetMapping(value = "/", params = {"name", "page", "size"})
    public List<EmployeeDto> getByName(@RequestParam("name") String name, @RequestParam int page, @RequestParam int size) {
        return hrEmployeeMapper.employeesToDtos(employeeServiceAncestor.findByName(name, page, size).toList());
    }

    ///api/employees/?d1=2005-01-01 00:00&d2=2020-01-01 00:00
    @GetMapping(value = "/", params = {"d1", "d2"})
    public List<EmployeeDto> getEmployedBetween(@RequestParam("d1") LocalDateTime d1,
                                                @RequestParam("d2") LocalDateTime d2) {
        return hrEmployeeMapper.employeesToDtos(employeeServiceAncestor.findByEmpSinceBetween(d1, d2));
    }

    @GetMapping(value = "/search")
    public List<EmployeeDto> getEmployeesByExample(@RequestBody EmployeeDto employee) {
        return hrEmployeeMapper.employeesToDtos(employeeServiceAncestor.findByExample(hrEmployeeMapper.dtoToEmployee(employee)));
    }


}
