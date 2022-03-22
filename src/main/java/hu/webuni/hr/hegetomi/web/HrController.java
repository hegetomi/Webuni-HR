package hu.webuni.hr.hegetomi.web;

import hu.webuni.hr.hegetomi.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class HrController {

    private final Map<Long, EmployeeDto> employees = new HashMap<>();

    {
        employees.put(0L,new EmployeeDto(0, "John Doe", "Architect", 2000, LocalDateTime.of(2000, 2, 10, 12, 12)));
        employees.put(1L,new EmployeeDto(1, "Jane Doe", "Lead dev", 1500, LocalDateTime.of(2010, 4, 15, 12, 12)));
        employees.put(2L,new EmployeeDto(2, "Joe Schmoe", "Senior dev", 1000, LocalDateTime.of(2015, 6, 20, 12, 12)));
        employees.put(3L,new EmployeeDto(3, "Jane Smith", "Medior dev", 500, LocalDateTime.of(2020, 8, 25, 12, 12)));
    }

    @GetMapping
    public List<EmployeeDto> getAll(){
        return new ArrayList<>(employees.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable long id){
        EmployeeDto selected = employees.get(id);
        if(selected!=null){
            return ResponseEntity.ok(selected);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto dto){
        if(!employees.containsKey(dto.getId())){
            employees.put(dto.getId(),dto);
            return ResponseEntity.ok(dto);
        } else{
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto dto) {
        if (employees.containsKey(id)) {
            dto.setId(id);
            employees.put(id, dto);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employees.remove(id);
    }

    //eg.:/api/employees/?salary=1000
    @GetMapping("/")
    public List<EmployeeDto> getSalaryGreaterThan(@RequestParam("salary") long amount){
        return employees.values()
                .stream()
                .filter(v-> v.getSalary()>amount)
                .collect(Collectors.toCollection(ArrayList::new));
    }




}
