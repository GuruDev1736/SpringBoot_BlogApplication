package com.Guruprasad.Blog.Service;

import com.Guruprasad.Blog.PayLoad.PostDTO;
import com.Guruprasad.Blog.PayLoad.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostResponse getallpost(int pageno , int pagesize , String sortBy , String sortDir);
    PostDTO getpostbyid(long id );
    PostDTO updatepost(PostDTO postDTO , long id);
    void deletepost(long id);

    List<PostDTO> getpostbycategory (Long id);
}
