package com.ictkerala.employee.repo;


import com.ictkerala.employee.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeModel,Integer> {

    //EmployeeModel updatedetails(EmployeeModel input);

}
