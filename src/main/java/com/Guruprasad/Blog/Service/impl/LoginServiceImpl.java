package com.Guruprasad.Blog.Service.impl;


import com.Guruprasad.Blog.Exception.BlogApiException;
import com.Guruprasad.Blog.Model.Role;
import com.Guruprasad.Blog.Model.User;
import com.Guruprasad.Blog.PayLoad.LoginDTO;
import com.Guruprasad.Blog.PayLoad.RegisterDTO;
import com.Guruprasad.Blog.Repository.RoleRepository;
import com.Guruprasad.Blog.Repository.UserRepository;
import com.Guruprasad.Blog.Security.JwtTokenProvider;
import com.Guruprasad.Blog.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager ;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider ;

    public LoginServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDTO) {

      Authentication authentication =  authenticationManager.authenticate(new
              UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(),loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {

        // add check for username exist in data base

        if (userRepository.existsByUsername(registerDTO.getUsername()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Username is already exist");
        }

        // add check for email exist in db

        if (userRepository.existsByEmail(registerDTO.getEmail()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email is already exist ");
        }

        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        String pass = passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(pass);
        Set<Role> roles = new HashSet<>();
        Role userrole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userrole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User has register successfully";
    }
}
