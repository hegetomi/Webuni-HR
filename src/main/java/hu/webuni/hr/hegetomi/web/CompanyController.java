package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.ResultPair;
import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.mapper.CompanyMapper;
import hu.webuni.hr.hegetomi.mapper.EmployeeMapper;
import hu.webuni.hr.hegetomi.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@Validated
@RestController
@RequestMapping("/api/companies")
public class CompanyController {


    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping
    public List<CompanyDto> getAll(@RequestParam(required = false, defaultValue = "false", name = "full") String full) {
        if ("true".equalsIgnoreCase(full)) {
            return new ArrayList<>(companyMapper.companiesToDtos(companyService.findAllWithEmployees()));
        } else {
            return new ArrayList<>(companyMapper.companiesToPartialCompanies(companyService.findAllWithEmployees()));
        }

    }

    @GetMapping("/{id}")
    public CompanyDto getById(@RequestParam(required = false, defaultValue = "false", name = "full") String full, @PathVariable long id) {
        if ("true".equalsIgnoreCase(full)) {
            return companyMapper.companyToDto(companyService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        } else {
            return companyMapper.mapWithoutEmployees(companyService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        }
    }

    @PostMapping
    public CompanyDto addCompany(@RequestBody @Valid CompanyDto dto) {
        return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(dto)));

    }

    @PutMapping("/{id}")
    public CompanyDto modifyCompany(@PathVariable long id, @RequestBody @Valid CompanyDto dto) {
        dto.setId(id);
        return companyMapper.companyToDto(companyService.edit(companyMapper.dtoToCompany(dto), id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companyService.delete(id);
    }


    @PostMapping("/{id}/employee")
    public EmployeeDto addEmployeeToCompany(@PathVariable long id, @RequestBody @Valid EmployeeDto dto) {
        return employeeMapper.employeeToDto(companyService.addEmployeeToCompany(id, employeeMapper.dtoToEmployee(dto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @DeleteMapping("/{compid}/employee/{id}")
    public void deleteEmployee(@PathVariable long compid, @PathVariable long id) {
        companyService.deleteEmployeeFromCompany(compid, id);
    }

    @PutMapping("/{id}/employees")
    public List<EmployeeDto> replaceEmployees(@PathVariable long id, @RequestBody List<@Valid EmployeeDto> dtos) {

        return employeeMapper.employeesToDtos(companyService.replaceEmployees(id, employeeMapper.DtosToEmployees(dtos))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

    }

    @GetMapping(value = "/", params = "salary-amount")
    public List<CompanyDto> findByEmployeeSalaryGreaterThan(@RequestParam long amount) {
        return companyMapper.companiesToDtos(companyService.findByEmployeeSalaryGreaterThan(amount));
    }

    @GetMapping(value = "/", params = "employed-amount")
    public List<CompanyDto> findByEmployeesMoreThan(int amount) {
        return companyMapper.companiesToDtos(companyService.findByEmployeesMoreThan(amount));
    }

    @GetMapping("/averages")
    public List<ResultPair<String,Double>> getTitlesAverageSalary() {
        return companyService.getTitlesAvgSalary();
    }

    @PostMapping("/position-minimum")
    public void raiseForAllAtPosition(@RequestParam String position, @RequestParam long newSalary) {
        companyService.raiseForAllAtPosition(position, newSalary);
    }

    @PostMapping("/{id}-position-minimum")
    public void raiseForAllAtCompanyAtPosition(@PathVariable long id,
                                               @RequestParam String position,
                                               @RequestParam long newSalary) {
        companyService.raiseForAllAtCompanyAtPosition(id, position, newSalary);
    }
}
