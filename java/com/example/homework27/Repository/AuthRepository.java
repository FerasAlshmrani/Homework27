package com.example.homework27.Repository;


import com.example.homework27.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
    User findUserById(Integer id);
}
