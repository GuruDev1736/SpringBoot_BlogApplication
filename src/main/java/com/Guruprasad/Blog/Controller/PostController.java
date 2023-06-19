package com.Guruprasad.Blog.Controller;

import com.Guruprasad.Blog.PayLoad.PostDTO;
import com.Guruprasad.Blog.PayLoad.PostResponse;
import com.Guruprasad.Blog.Service.PostService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService ;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post rest api

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO)
    {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    // get all post rest api
    @GetMapping
    public PostResponse getallpost
    (
            @RequestParam(value = "pageNo" , defaultValue = "0" , required = false) int pageNo ,
            @RequestParam(value = "pageSize" , defaultValue = "10" , required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    )
    {
        return postService.getallpost(pageNo,pageSize,sortBy,sortDir);
    }

    // getpost by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getpostbyid(@PathVariable(name = "id") long id)
    {
        return ResponseEntity.ok(postService.getpostbyid(id));
    }

    //update post rest api by id

    @PutMapping("{id}")
    public ResponseEntity<PostDTO> updatepost( @Valid @RequestBody PostDTO postDTO , @PathVariable(name = "id") long id)
    {
         PostDTO postresponse = postService.updatepost(postDTO,id);
         return new ResponseEntity<>(postresponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deletepost(@PathVariable(name = "id") long id)
    {
        postService.deletepost(id);
        return new ResponseEntity<>("The Post of the following id :"+id+" has been deleted ",HttpStatus.OK);
    }

}
