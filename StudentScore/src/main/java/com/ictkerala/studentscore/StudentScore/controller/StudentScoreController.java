package com.ictkerala.studentscore.StudentScore.controller;


import com.ictkerala.studentscore.StudentScore.model.StudentScoreModel;
import com.ictkerala.studentscore.StudentScore.repo.StudentScorerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/student")
public class StudentScoreController {

    @Autowired
    StudentScorerepo repo;
    private Map<Long, String> students = new HashMap<>();

    @PostMapping("/student")
    public ResponseEntity<Map<String,String>> create(@RequestBody StudentScoreModel input){
       Map<String,String> response=new HashMap<>();
       try{


           StudentScoreModel student = repo.save(input);
            if(!student.getStudId().isEmpty()){
                ResponseEntity.status(HttpStatus.CREATED);
                response.put("status",  "Successfully added Student details");
            }else {
                response.put("status", "Failed to add Student details");
            }

       } catch (Exception e) {
           response.put("status","Error occured");
       }


        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/student")
    public ResponseEntity<List<StudentScoreModel>> viewAll() {
        Map<String, String> response = new HashMap<>();
        List<StudentScoreModel> students = null;
        try {
            students = repo.findAll();
            if (students == null || students.isEmpty()){
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students);
            }
        } catch (Exception e) {

            response.put("status","Error occured");
        }

        return ResponseEntity.ok(students);
    }




    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        if (students.containsKey(id)) {
            students.remove(id);
            return ResponseEntity.ok("Student deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found.");
        }
    }
}
