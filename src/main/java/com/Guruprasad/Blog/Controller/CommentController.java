package com.Guruprasad.Blog.Controller;

import com.Guruprasad.Blog.PayLoad.CommentDTO;
import com.Guruprasad.Blog.Service.CommentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService ;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId ,  @Valid @RequestBody CommentDTO commentDTO){

        return new ResponseEntity<>(commentService.createcomment(postId,commentDTO) , HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getallcomment(@PathVariable(value = "postId") Long postId)
    {
        return commentService.getallcomment(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getcommentbyid(@PathVariable(value = "postId") long postId,@PathVariable(value = "commentId") long commentId)
    {

        return new ResponseEntity<>(commentService.getcommentbyid(postId,commentId) , HttpStatus.OK);

    }
    @PutMapping("/posts/{postId}/comments/{commentId}")

    public ResponseEntity<CommentDTO> updatecomment (@PathVariable(value = "postId") long postId,
        @PathVariable(value = "commentId") long commentId ,@Valid @RequestBody CommentDTO commentDTO){

        CommentDTO commentResponse = commentService.updatecomment(postId,commentId,commentDTO);
        return new ResponseEntity<>(commentResponse,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deletecomment (@PathVariable(value = "postId") long postId , @PathVariable(value = "commentId") long commentId)
    {
        commentService.deletecomment(postId,commentId);
        return new ResponseEntity<>("The Comment of the PostId : "+postId+" Has Been Deleted by the reference of commentId : "+commentId , HttpStatus.OK);
    }

}
