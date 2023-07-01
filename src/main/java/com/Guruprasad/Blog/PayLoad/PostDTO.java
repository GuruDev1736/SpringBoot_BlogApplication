package com.Guruprasad.Blog.PayLoad;

import lombok.Data;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
public class PostDTO {
    private long id ;

    @NotEmpty
    @Size(min = 2 , message = "Post title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10 , message = "Post description should have atleast 10 characters")
    private String description;
    @NotEmpty
    @Size(min = 5 , message = "Post content should have atleast 5 characters")
    private String content;
    private Set<CommentDTO> comments;

    private Long categoryId;


}
