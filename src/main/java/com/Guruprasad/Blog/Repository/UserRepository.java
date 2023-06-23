package com.Guruprasad.Blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Guruprasad.Blog.Model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    
//    Optional<User> findByEmail(String email);
//    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String email , String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    

}
