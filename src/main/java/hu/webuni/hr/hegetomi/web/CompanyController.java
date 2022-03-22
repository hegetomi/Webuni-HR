package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.company.CompanyDto;
import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import hu.webuni.hr.hegetomi.dto.company.CompanyInterface;
import hu.webuni.hr.hegetomi.dto.company.TruncatedCompanyDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final Map<Long, CompanyDto> companies = new HashMap<>();

    {
        companies.put(0L, new CompanyDto(0L, 123456789L, "Name2", "Address1",
                new ArrayList<>(List.of(
                        new EmployeeDto(0, "EmpName1", "Janitor", 500, LocalDateTime.now()),
                        new EmployeeDto(1, "EmpName2", "Security guard", 600, LocalDateTime.now())))));
        companies.put(1L, new CompanyDto(1L, 987654321L, "Name2", "Address2",
                new ArrayList<>(List.of(
                        new EmployeeDto(0, "EmpName3", "Assistant", 700, LocalDateTime.now()),
                        new EmployeeDto(1, "EmpName4", "Vice president", 800, LocalDateTime.now())))));
    }


    @GetMapping
    public List<CompanyInterface> getAll(@RequestParam(required = false, defaultValue = "false", name = "full") String full) {
        if("true".equalsIgnoreCase(full)){
            return new ArrayList<>(companies.values());
        } else{
            return companies.values().stream()
                    .map(v-> new TruncatedCompanyDto(v.getId(),v.getRegistrationNumber(),v.getName(),v.getAddress()))
                    .collect(Collectors.toCollection(ArrayList::new));
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable long id) {
        CompanyDto selected = companies.get(id);
        if (selected != null) {
            return ResponseEntity.ok(selected);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto dto) {
        if (!companies.containsKey(dto.getId())) {
            companies.put(dto.getId(), dto);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto dto) {
        if (companies.containsKey(id)) {
            dto.setId(id);
            companies.put(id, dto);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable long id) {
        companies.remove(id);
    }

}