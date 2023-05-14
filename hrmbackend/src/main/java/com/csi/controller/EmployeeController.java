package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.repo.EmployeeRepo;
import com.csi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/")
    public ResponseEntity<Employee> saveData(@Valid @RequestBody Employee employee)
    {
        return new ResponseEntity<>(employeeServiceImpl.saveData(employee), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId)
    {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllData()
    {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateData(@PathVariable int empId,@RequestBody Employee employee)
    {
        Employee employee1=employeeServiceImpl.getDataById(empId).orElseThrow(()->new RecordNotFoundException("Employee Id does not exists"));

        employee1.setEmpFirstName(employee.getEmpFirstName());
        employee1.setEmpLastName(employee.getEmpLastName());
        employee1.setEmpEmailId(employee.getEmpEmailId());

        return ResponseEntity.ok(employeeServiceImpl.updateData(employee1));
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId)
    {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data deleted successfully");
    }
}
