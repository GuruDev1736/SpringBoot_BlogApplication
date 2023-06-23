package com.Guruprasad.Blog.Service;

import com.Guruprasad.Blog.PayLoad.LoginDTO;
import com.Guruprasad.Blog.PayLoad.RegisterDTO;

public interface LoginService {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
