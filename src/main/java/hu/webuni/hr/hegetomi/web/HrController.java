package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.mapper.EmployeeMapper;
import hu.webuni.hr.hegetomi.service.employee.EmployeeServiceAncestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class HrController {


    @Autowired
    EmployeeServiceAncestor ancestor;

    @Autowired
    EmployeeMapper mapper;

    @GetMapping
    public List<EmployeeDto> getAll() {
        // EmployeeAncestor ancestor = (EmployeeAncestor) service;
        return mapper.employeesToDtos(ancestor.findAll());
    }

    @GetMapping("/{id}")
    public EmployeeDto getById(@PathVariable long id) {
        return mapper.employeeToDto(ancestor.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public EmployeeDto addEmployee(@RequestBody @Valid EmployeeDto dto) {

        return mapper.employeeToDto(ancestor.save(mapper.dtoToEmployee(dto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN)));
    }

    @PutMapping("/{id}")
    public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto dto) {
        return mapper.employeeToDto(ancestor.edit(mapper.dtoToEmployee(dto),id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        ancestor.delete(id);
    }

    //eg.:/api/employees/?salary=1000
    @GetMapping("/")
    public List<EmployeeDto> getSalaryGreaterThan(@RequestParam("salary") long amount) {
        List<EmployeeDto> employees = mapper.employeesToDtos(ancestor.findAll());
        return employees.stream()
                .filter(v -> v.getSalary() > amount)
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
