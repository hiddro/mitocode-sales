package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.CategoryDTO;
import com.mitocode.model.entity.Category;
import com.mitocode.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    @Qualifier("categoryMapper")
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
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO category) throws Exception{
        Category categ = categoryService.save(modelMapper.map(category, Category.class));

        return new ResponseEntity<>(modelMapper.map(categ, CategoryDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO category) throws Exception{
        Category categ = categoryService.update(modelMapper.map(category, Category.class));

        return new ResponseEntity<>(modelMapper.map(categ, CategoryDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Category categ = categoryService.findById(id);

        if(categ == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API http://localhost:8080/v3/api-docs / http://localhost:8080/swagger-ui.html
}
