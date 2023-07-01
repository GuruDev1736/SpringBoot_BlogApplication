package com.Guruprasad.Blog.Service.impl;

import com.Guruprasad.Blog.Exception.ResourceNotFoundException;
import com.Guruprasad.Blog.Model.Category;
import com.Guruprasad.Blog.PayLoad.CategoryDTO;
import com.Guruprasad.Blog.Repository.CategoryRepository;
import com.Guruprasad.Blog.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        Category category = mapper.map(categoryDTO , Category.class);
        Category saved = categoryRepository.save(category);

        return mapper.map(saved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO getcategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","id",categoryId));


        return mapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getallcategory() {

        List<Category> categories = categoryRepository.findAll();


        return categories.stream().map((category)-> mapper.map(category,CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updatecategory(CategoryDTO categoryDTO, Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category","id",id));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category update = categoryRepository.save(category);

        return mapper.map(update, CategoryDTO.class);
    }

    @Override
    public void deletecategory(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category","id",id));
        categoryRepository.delete(category);
    }

    @Override
    public void deleteall() {
        categoryRepository.deleteAll();
    }


}
