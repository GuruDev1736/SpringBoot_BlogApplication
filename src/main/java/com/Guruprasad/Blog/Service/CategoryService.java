package com.Guruprasad.Blog.Service;

import com.Guruprasad.Blog.PayLoad.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService  {

    CategoryDTO addCategory (CategoryDTO categoryDTO);
    CategoryDTO getcategory (Long categoryId );
    List<CategoryDTO> getallcategory();

    CategoryDTO updatecategory (CategoryDTO categoryDTO , Long id);

    void deletecategory(Long id);
    void deleteall();


}
