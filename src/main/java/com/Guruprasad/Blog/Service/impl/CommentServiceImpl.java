package com.Guruprasad.Blog.Service.impl;

import com.Guruprasad.Blog.Exception.BlogApiException;
import com.Guruprasad.Blog.Exception.ResourceNotFoundException;
import com.Guruprasad.Blog.Model.Comment;
import com.Guruprasad.Blog.Model.Post;
import com.Guruprasad.Blog.PayLoad.CommentDTO;
import com.Guruprasad.Blog.PayLoad.PostDTO;
import com.Guruprasad.Blog.Repository.CommentRepository;
import com.Guruprasad.Blog.Repository.PostRepository;
import com.Guruprasad.Blog.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository ;
    private ModelMapper mapper ;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository , PostRepository postRepository , ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDTO createcomment(long postId, CommentDTO commentDTO) {
            Comment comment = mapintoentity(commentDTO);

            // Retrive post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));

        // set post to comment entity
        comment.setPost(post);

        // comment entity into DB
        Comment newcomment = commentRepository.save(comment);

        return mapintodto(newcomment);
    }

    @Override
    public List<CommentDTO> getallcomment( long postId) {

        // retrive comments by post id
        List<Comment> comments = commentRepository.findByPostId(postId);

        //  Convert list of comment entities to list comment dto's
        return comments.stream().map(comment -> mapintodto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getcommentbyid(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment not found for the following post ");
        }

        return mapintodto(comment);


    }

    @Override
    public CommentDTO updatecomment(long postId, long commentId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment not found for the following post ");
        }

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment updatecomment = commentRepository.save(comment);

        return mapintodto(updatecomment);
    }

    @Override
    public void deletecomment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));

        if (!comment.getPost().getId().equals(post.getId()))
        {
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment not found for the following post ");
        }

        commentRepository.delete(comment);

    }


    private CommentDTO mapintodto(Comment comment)
    {
        CommentDTO commentDTO = mapper.map(comment , CommentDTO.class);
//
//        CommentDTO commentDTO  = new CommentDTO();
//        commentDTO.setId(comment.getId());
//        commentDTO.setName(comment.getName());
//        commentDTO.setEmail(comment.getEmail());
//        commentDTO.setBody(comment.getBody());
        return commentDTO;

    }

    //converted dto to entity

    private Comment mapintoentity (CommentDTO commentDTO)
    {
        Comment comment = mapper.map(commentDTO,Comment.class);

//       Comment comment = new Comment();
//        comment.setName(commentDTO.getName());
//        comment.setEmail(commentDTO.getEmail());
//        comment.setBody(commentDTO.getBody());
//        comment.setId(commentDTO.getId());
        return comment;
    }
}
