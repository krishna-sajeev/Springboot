package com.ictkerala.blogApp;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    PostRepo repo;

    BlogAppController blog ;

    @PostMapping("/addPost")
    public ResponseEntity<Map<String,String>> createPost(HttpServletRequest request, @RequestBody PostModel input){
        Map<String,String> response = new HashMap<>();
        try{
            String token=request.getHeader("token");
           // String fetchToken = BlogAppController.tokenStore.get("token");
            if (token==null || !BlogAppController.tokenStore.containsKey(token)){
               response.put("status","Unauthorized access");

                throw new RuntimeException("Unauthorized");

            }
          PostModel obj = repo.save(input);
          if(obj.getId()!=0 ){
              response.put("status","Successfully posted");
          }
          else{
              response.put("status","Error occurred");
          }
        }catch (Exception e){
            System.out.println(e.toString());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/viewAll")
    public ResponseEntity<List<Map<String,String>>> viewAllPost(){
      //  List<PostModel> response = new ArrayList<>();
        List<Map<String,String>> posts = List.of();
        try{
            posts=repo.viewALlPost();

        }
        catch (Exception e){

        }
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/viewMyPost")
    public ResponseEntity<List<Map<String,String>>> viewMyPost(@RequestBody PostModel input){
        List<Map<String,String>> posts = List.of();
        try{
            posts=repo.viewMyPost(input.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(posts);
    }
}
