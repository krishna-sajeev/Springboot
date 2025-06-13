package com.ictkerala.blogApp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class BlogAppController {



    @Autowired
    BlogRepo repo;

    public static final Map<String,String> tokenStore=new HashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signup(@RequestBody BlogModel input){
        Map<String,String> response =new HashMap<>();

        try{
            BlogModel blog = repo.save(input);
            if(blog.getId()!=0){
                response.put("status","User details added succesfully");
            }
            else{
                response.put("status","Error");
            }
        } catch (Exception e) {
            
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody BlogModel input){
        Map<String, Object> response = new HashMap<>();

        try {
            List<BlogModel> loginData = repo.login(input.getUsername(),input.getPassword());
            if(loginData.isEmpty()){
                response.put("status","Login failed");
            }
            else {

                String token = UUID.randomUUID().toString();
                tokenStore.put(token,input.getUsername());

                response.put("status","Login Successfully");
                response.put("token",token);
               // System.out.println(loginData.get(0).getId());
                response.put("UserId",loginData.get(0).getId());

            }
        }
        catch (Exception e){

        }
        return ResponseEntity.ok(response);
    }
}
