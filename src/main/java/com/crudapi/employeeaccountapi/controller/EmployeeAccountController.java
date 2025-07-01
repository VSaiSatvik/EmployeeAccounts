package com.crudapi.employeeaccountapi.controller;

import com.crudapi.employeeaccountapi.model.EmployeeAccount;
import com.crudapi.employeeaccountapi.repository.EmployeeAccountRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeAccountController {

    private final EmployeeAccountRepository repo;

    public EmployeeAccountController(EmployeeAccountRepository repo) {
        this.repo = repo;
    }

    // GET all employees
    @GetMapping
    public List<EmployeeAccount> getAllEmployees() {
        return repo.findAll();
    }

    // GET employee by Id
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeAccount> getEmployeeById(@PathVariable int id) {
        Optional<EmployeeAccount> employee = repo.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST - create new employee
    @PostMapping
    public EmployeeAccount createEmployee(@RequestBody EmployeeAccount employee) {
        return repo.save(employee);
    }

    // PUT - update employee by Id
    @PutMapping("/employee/{Id}")
    public ResponseEntity<EmployeeAccount> updateEmployee(@PathVariable Long id,
                                                          @RequestBody EmployeeAccount updatedEmployee) {
        Optional<EmployeeAccount> existingEmployee = repo.findById(id);
        if (existingEmployee.isPresent()) {
            EmployeeAccount employee = existingEmployee.get();
            employee.setBankAccountNumber(updatedEmployee.getBankAccountNumber());
            employee.setBankName(updatedEmployee.getBankName());
            return ResponseEntity.ok(repo.save(employee));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - delete employee by Id
    @DeleteMapping("/employee/{employeeId}")
    @Transactional
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}
