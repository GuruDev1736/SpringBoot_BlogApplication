package com.Guruprasad.Blog.Repository;

import com.Guruprasad.Blog.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByCategoryId(Long categoryId);
}
