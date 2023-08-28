package com.example.homework27.Service;

import com.example.homework27.Api.ApiException;
import com.example.homework27.Model.Blog;
import com.example.homework27.Model.User;
import com.example.homework27.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public void addBlog(User user, Blog blog) {
        blog.setUser(user);
        blogRepository.save(blog);

    }

    public void updateBlod(User user, Blog blog,Integer id) {

        Blog blog1 = blogRepository.findBlogsByUserAndId(user,id);

        if(blog1== null){
            throw new ApiException("Blog not found");
        }
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog1);
        /*Blog blog1 = blogRepository.findBlogById(id);
        if(blog1== null){
            throw new ApiException("This blog is already in");
        }
        if(user.getBlogSet().contains(blog1)){
            blog1.setTitle(blog.getTitle());
            blog1.setBody(blog.getBody());
            blog1.setUser(user);
            blogRepository.save(blog1);
        }
        else{
            throw new ApiException("This blog is not yours");
        }*/
    }

    public void deleteBlog(User user,Integer id) {
        Blog blog = blogRepository.findBlogsByUserAndId(user,id);
        if(blog == null){
            throw new ApiException("Blog not found");
        }
        blogRepository.delete(blog);
    }

    public List<Blog> getAllBlogsByUser(User user){
        return blogRepository.findBlogsByUser(user);

    }

    public Blog findBlogById(User user,Integer id){
        Blog blog = blogRepository.findBlogsByUserAndId(user,id);
        if(blog == null){
            throw new ApiException("Blog not found");
        }
        return blog;
    }

    public List<Blog> findBlogByTitle(User user,String title){

        List<Blog> blogs =  blogRepository.findBlogsByTitle(user,title);

        if(blogs.isEmpty()){
            throw new ApiException("No blog in this user");
        }

        return blogs;

    }
}
