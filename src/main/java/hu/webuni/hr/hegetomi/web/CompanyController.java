package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.dto.company.CompanyInterface;
import hu.webuni.hr.hegetomi.mapper.CompanyMapper;
import hu.webuni.hr.hegetomi.mapper.EmployeeMapper;
import hu.webuni.hr.hegetomi.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {


    @Autowired
    CompanyService service;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    EmployeeMapper employeeMapper;


    @GetMapping
    public List<CompanyInterface> getAll(@RequestParam(required = false, defaultValue = "false", name = "full") String full) {
        if ("true".equalsIgnoreCase(full)) {
            return new ArrayList<>(companyMapper.companiesToDtos(service.findAll()));
        } else {
            return new ArrayList<>(companyMapper.companiesToTruncatedDtos(service.findAll()));
        }

    }

    @GetMapping("/{id}")
    public CompanyInterface getById(@PathVariable long id) {
        return companyMapper.companyToDto(service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public CompanyInterface addCompany(@RequestBody CompanyDto dto) {
        return companyMapper.companyToDto(service.save(companyMapper.dtoToCompany(dto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN)));

    }

    @PutMapping("/{id}")
    public CompanyInterface modifyCompany(@PathVariable long id, @RequestBody CompanyDto dto) {
        return companyMapper.companyToDto(service.edit(companyMapper.dtoToCompany(dto), id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        service.delete(id);
    }


    @PostMapping("/{id}/employee")
    public EmployeeDto addEmployeeToCompany(@PathVariable long id, @RequestBody EmployeeDto dto) {
        return employeeMapper.employeeToDto(service.addEmployeeToCompany(id, employeeMapper.dtoToEmployee(dto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{compid}/employee/{id}")
    public void deleteEmployee(@PathVariable long compid, @PathVariable long id) {
        service.deleteEmployeeFromCompany(compid, id);
    }

    @PutMapping("/{id}/employees")
    public List<EmployeeDto> replaceEmployees(@PathVariable long id, @RequestBody List<EmployeeDto> dtos) {

        return employeeMapper.employeesToDtos(service.replaceEmployees(id, employeeMapper.DtosToEmployees(dtos))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

    }

}
