package com.ictkerala.blogApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PostRepo extends JpaRepository<PostModel,Integer> {

    @Query(value = "SELECT pm.`id`,pm.`description`, pm.`post_title`, pm.`user_id` , bm.username FROM `post_model` pm JOIN blog_model bm ON pm.user_id=bm.id WHERE pm.`user_id`=?1",nativeQuery = true)
    List<Map<String,String>> viewMyPost(String id);

    @Query(value = "SELECT pm.`id`,pm.`description`, pm.`post_title`, pm.`user_id` , bm.username FROM `post_model` pm JOIN blog_model bm ON pm.user_id=bm.id",nativeQuery = true)
    List<Map<String,String>> viewALlPost();
}
