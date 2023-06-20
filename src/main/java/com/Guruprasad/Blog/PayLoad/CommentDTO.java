package com.Guruprasad.Blog.PayLoad;

import jakarta.validation.constraints.Email;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class CommentDTO {
    private long id ;
    
    @NotEmpty
    @Size(min = 5 , max = 50 , message = "Please Enter Your Full name")
    private String name ;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email ;
    @NotEmpty
    @Size(min = 5 , max = 100 , message = "Comment body should atleast 5 Characters")
    private String body ;
}
