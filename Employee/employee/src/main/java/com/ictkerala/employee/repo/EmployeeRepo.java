package com.ictkerala.employee.repo;


import com.ictkerala.employee.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<EmployeeModel,Integer> {


}
