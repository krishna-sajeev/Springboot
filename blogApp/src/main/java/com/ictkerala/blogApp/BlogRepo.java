package com.ictkerala.blogApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepo extends JpaRepository<BlogModel,Integer> {

    @Query(value = "SELECT `id`, `address`, `password`, `phone_number`, `username` FROM `blog_model` WHERE `username`=?1 AND `password`=?2",nativeQuery = true)
    List<BlogModel> login(String username,String password);
}
