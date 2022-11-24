package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.CategoryDTO;
import com.mitocode.model.entity.Category;
import com.mitocode.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = categoryService.findAll()
                                            .stream()
                                            .map(cat -> modelMapper.map(cat, CategoryDTO.class))
                                            .collect(Collectors.toList());
//        List<CategoryDTO> list = categoryService.findAll()
//                                            .stream()
//                                            .map(cat -> {
//                                                CategoryDTO dto = new CategoryDTO();
//                                                dto.setId(cat.getIdCategory());
//                                                dto.setNameCategory(cat.getName());
//                                                dto.setDescriptionCategory(cat.getDescription());
//                                                dto.setEnabledCategory(cat.isEnabled());
//
//                                                return dto;
//                                            })
//                                            .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO category = modelMapper.map(categoryService.findById(id), CategoryDTO.class);

        if(category == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) throws Exception{
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category) throws Exception{
        return new ResponseEntity<>(categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
