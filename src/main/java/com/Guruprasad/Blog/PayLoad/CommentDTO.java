package com.Guruprasad.Blog.PayLoad;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class CommentDTO {
    private long id ;
    
    @NotEmpty
    @Size(min = 5 , max = 50 , message = "Please Enter Your Full name")
    private String name ;
    @NotEmpty
    @Size(min = 10 , message = "Please Enter the valid Email")
    private String email ;
    @NotEmpty
    @Size(min = 5 , max = 100 , message = "Comment body should atleast 5 Characters")
    private String body ;
}
