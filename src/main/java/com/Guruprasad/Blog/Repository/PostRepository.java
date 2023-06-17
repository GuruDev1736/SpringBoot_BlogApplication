package com.Guruprasad.Blog.Repository;

import com.Guruprasad.Blog.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
