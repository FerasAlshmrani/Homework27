package com.example.homework27.Controller;

import com.example.homework27.Api.ApiResponse;
import com.example.homework27.Model.User;
import com.example.homework27.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        userService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("user registered successfully"));
    }
    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User olduser, @RequestBody @Valid User updatedUser){
        userService.update(olduser,updatedUser);
        return ResponseEntity.status(200).body(new ApiResponse("user Updated Successfully"));
    }
    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal User user){
        userService.delete(user);
        return ResponseEntity.status(200).body(new ApiResponse("user Deleted"));
    }
}
