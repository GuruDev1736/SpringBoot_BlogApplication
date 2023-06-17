package com.Guruprasad.Blog.Service.impl;

import com.Guruprasad.Blog.Exception.ResourceNotFoundException;
import com.Guruprasad.Blog.Model.Post;
import com.Guruprasad.Blog.PayLoad.PostDTO;
import com.Guruprasad.Blog.PayLoad.PostResponse;
import com.Guruprasad.Blog.Repository.PostRepository;
import com.Guruprasad.Blog.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper ;

    public PostServiceImpl(PostRepository postRepository , ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper ;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Post post = mapintoentity(postDTO);

        Post newpost =  postRepository.save(post);

        PostDTO postresponse = mapintodto(newpost);

        return postresponse;
    }


    @Override
    public PostResponse getallpost(int pageNo , int pageSize , String sortBy , String sortDir) {
        // create pageble instance

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);


        Page<Post> posts = postRepository.findAll(pageable);

//        get content for page object
        List<Post> listofpost = posts.getContent();

        List<PostDTO> content =  listofpost.stream().map(post -> mapintodto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(postResponse.getPageSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDTO getpostbyid(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        return mapintodto(post);

    }

    @Override
    public PostDTO updatepost(PostDTO postDTO, long id) {
        // get post by id from the data base
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatepost = postRepository.save(post);
        return mapintodto(updatepost);


    }


    // deleting the post from the database
    @Override
    public void deletepost(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        postRepository.delete(post);

    }


    // converted entity into dto
    private PostDTO mapintodto(Post post)
    {
        PostDTO postDTO = mapper.map(post,PostDTO.class);
//        PostDTO postDTO  = new PostDTO();
//        postDTO.setId(post.getId());
//        postDTO.setTitle(post.getTitle());
//        postDTO.setDescription(post.getDescription());
//        postDTO.setContent(post.getContent());

        return postDTO;

    }

    //converted dto to entity

    private Post mapintoentity (PostDTO postDTO)
    {
        Post post = mapper.map(postDTO,Post.class);
//        Post post = new Post();
//        post.setTitle(postDTO.getTitle());
//        post.setDescription(postDTO.getDescription());
//        post.setContent(postDTO.getContent());
        return post;
    }

}
