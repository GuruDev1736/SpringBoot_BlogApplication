package com.Guruprasad.Blog.Controller;


import com.Guruprasad.Blog.PayLoad.JWTAuthResponse;
import com.Guruprasad.Blog.PayLoad.LoginDTO;
import com.Guruprasad.Blog.PayLoad.RegisterDTO;
import com.Guruprasad.Blog.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private LoginService loginService ;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Build login rest api

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO)
    {
        String token  = loginService.login(loginDTO);
        JWTAuthResponse jwtAuthReponse = new JWTAuthResponse();
        jwtAuthReponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthReponse);


    }


    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDTO registerDTO)
    {
        String response = loginService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
