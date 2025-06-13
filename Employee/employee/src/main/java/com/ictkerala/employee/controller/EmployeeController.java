package com.ictkerala.employee.controller;


import com.ictkerala.employee.model.EmployeeModel;
import com.ictkerala.employee.repo.EmployeeRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepo repo;


    @GetMapping("/viewEmployeeDetails")
    public ResponseEntity<List<EmployeeModel>> viewAll() {
        Map<String, String> response = new HashMap<>();
        List<EmployeeModel> employee = List.of();
        try {
            employee = repo.findAll();


        } catch (Exception e) {
            response.put("status", "Error");
            //return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/viewEmployeeDetails/{id}")
    public ResponseEntity<Optional<EmployeeModel>> viewById(@PathVariable int id) {
        Map<String, String> response = new HashMap<>();
        Optional<EmployeeModel> employee = Optional.empty();
        try {
            employee = repo.findById(id);


        } catch (Exception e) {
            response.put("status", "Error");
            //return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(employee);
    }


    @PostMapping("/addEmployeeDetails")
    public ResponseEntity<Map<String,String>> addEmployee(@RequestBody EmployeeModel input){
        Map<String,String> response = new HashMap<>();
        try{
            EmployeeModel emp = repo.save(input);
            if (emp.getEmpId() != 0) {
                response.put("status", "Employee Added successfully");
            } else {
                response.put("status", "Failed to add Employee details");
            }

        } catch (Exception e) {
            response.put("status", "Error");
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel updatedEmployee) {
        try {
            Optional<EmployeeModel> optionalEmployee = repo.findById(Math.toIntExact(id));

            if (!optionalEmployee.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            EmployeeModel existingEmployee = optionalEmployee.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setSalary(updatedEmployee.getSalary());

            EmployeeModel savedEmployee = repo.save(existingEmployee);
            return ResponseEntity.ok(savedEmployee);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating employee: " + e.getMessage());
        }
    }


    @DeleteMapping("/removeEmployee/{id}")
    public ResponseEntity<Map<String,String>> deleteEmployeeDetails(@PathVariable int id){
        Map<String,String> response = new HashMap<>();
        Optional<EmployeeModel> employeeModel;
        try{

            employeeModel=repo.findById(id);
            if (employeeModel.isPresent()){
                repo.delete(employeeModel.get());
                response.put("status","Employee detail removed");
            }

        }catch (Exception e){
            throw new RuntimeException();
        }
        return ResponseEntity.ok(response);
    }



}
