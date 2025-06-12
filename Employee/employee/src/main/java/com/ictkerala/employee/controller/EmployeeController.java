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
    public  ResponseEntity<Map<String,String>> updateEmployee(@PathVariable Long id ,@RequestBody EmployeeModel input){
        Map<String,String> response = new HashMap<>();
        Optional<EmployeeModel> employeeModel;
        try{
            employeeModel=repo.findById(Math.toIntExact(id));
            if (employeeModel.isPresent()){
                EmployeeModel existingemployee = employeeModel.get();
                existingemployee.setName(input.getName());
                existingemployee.setDepartment(input.getDepartment());
                existingemployee.setSalary(input.getSalary());
                existingemployee.setEmpId(input.getEmpId());
                repo.save(existingemployee);
                System.out.println(existingemployee.getEmpId());
                response.put("status","Employee detail updated");
            }

        } catch (Exception e) {
            response.put("status","Error occured . Not found");
        }
        return ResponseEntity.ok(response);
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
