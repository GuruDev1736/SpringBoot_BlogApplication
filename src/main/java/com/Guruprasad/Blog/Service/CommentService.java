package com.Guruprasad.Blog.Service;

import com.Guruprasad.Blog.Model.Comment;
import com.Guruprasad.Blog.PayLoad.CommentDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CommentService {
    CommentDTO createcomment(long postId ,CommentDTO commentDTO );
    List<CommentDTO> getallcomment(long postId);
    CommentDTO getcommentbyid(long postId , long commentId);
    CommentDTO updatecomment(long postId , long commentId , CommentDTO commentDTO);

    void deletecomment(long postId , long commentId);
}
