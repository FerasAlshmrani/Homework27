package com.example.homework27.Service;

import com.example.homework27.Api.ApiException;
import com.example.homework27.Model.Blog;
import com.example.homework27.Model.User;
import com.example.homework27.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void register(User user){
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");
        userRepository.save(user);
    }

    public void update(User oldUser,User updatedUser){
        String hash = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
        updatedUser.setPassword(hash);

        oldUser.setUsername(updatedUser.getUsername());
        oldUser.setPassword(updatedUser.getPassword());
        userRepository.save(oldUser);

    }

    public void delete(User user){
        userRepository.delete(user);
    }


}
