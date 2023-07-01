package com.Guruprasad.Blog.Repository;

import com.Guruprasad.Blog.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
