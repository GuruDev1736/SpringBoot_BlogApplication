package com.Guruprasad.Blog.PayLoad;

import com.Guruprasad.Blog.Model.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private long id ;
    private String title;
    private String description;
    private String content;
    private Set<CommentDTO> comments;


}
