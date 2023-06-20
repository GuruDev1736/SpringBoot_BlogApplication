package com.Guruprasad.Blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Guruprasad.Blog.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
//    Optional<User> findByEmail(String email);
//    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String email , String username);

//    Boolean existByUsername(String username);
//    Boolean existByEmail(String email);
    

}
