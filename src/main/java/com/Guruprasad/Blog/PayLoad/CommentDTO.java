package com.Guruprasad.Blog.PayLoad;

import lombok.Data;

import java.util.Set;

@Data
public class CommentDTO {
    private long id ;
    private String name ;
    private String email ;
    private String body ;
}
