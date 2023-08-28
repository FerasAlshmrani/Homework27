package com.example.homework27.Repository;

import com.example.homework27.Model.Blog;
import com.example.homework27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    Blog findBlogById(Integer id);

    List<Blog> findBlogsByUser(User user);
    @Query("select b from Blog b where b.user =?1 and b.id =?2")
    Blog findBlogsByUserAndId(User user ,Integer id);


    @Query("select b from Blog b where b.title = ?2 and b.user =?1")
    List<Blog> findBlogsByTitle(User user,String title);
}
