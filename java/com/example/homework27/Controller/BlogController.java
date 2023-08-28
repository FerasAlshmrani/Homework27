package com.example.homework27.Controller;

import com.example.homework27.Api.ApiResponse;
import com.example.homework27.Model.Blog;
import com.example.homework27.Model.User;
import com.example.homework27.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/get")
    public ResponseEntity findAll() {
        return ResponseEntity.status(200).body(blogService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@RequestBody @Valid Blog blog) {
        blogService.addBlog(user, blog);
        return ResponseEntity.status(200).body(new ApiResponse("Blog added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog,@PathVariable Integer id) {
        blogService.updateBlod(user,blog,id);
        return ResponseEntity.status(200).body(new ApiResponse("Blog updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        blogService.deleteBlog(user, id);
        return ResponseEntity.status(200).body(new ApiResponse("Blog deleted successfully"));
    }

    @GetMapping("/get-user-blogs")
    public ResponseEntity getAllBlogsByUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getAllBlogsByUser(user));
    }

    @GetMapping("/get-blog-by-title/{title}")
    public ResponseEntity findBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title) {
        return ResponseEntity.status(200).body(blogService.findBlogByTitle(user,title));
    }

    @GetMapping("/get-blog-by-id/{id}")
    public ResponseEntity findBlogById(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(blogService.findBlogById(user,id));
    }




}
