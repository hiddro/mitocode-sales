package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.RoleDTO;
import com.mitocode.model.entity.Role;
import com.mitocode.service.RoleService;
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
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    @Qualifier("roleMapper")
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> readAll() throws Exception{
        List<RoleDTO> list = roleService.findAll()
                                            .stream()
                                            .map(role -> modelMapper.map(role, RoleDTO.class))
                                            .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable("id") Integer id) throws Exception{
        RoleDTO role = modelMapper.map(roleService.findById(id), RoleDTO.class);

        if(role == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> save(@Valid @RequestBody RoleDTO role) throws Exception{
        Role rle = roleService.save(modelMapper.map(role, Role.class));

        return new ResponseEntity<>(modelMapper.map(rle, RoleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO role) throws Exception{
        Role rle = roleService.update(modelMapper.map(role, Role.class));

        return new ResponseEntity<>(modelMapper.map(rle, RoleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Role rle = roleService.findById(id);

        if(rle == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API http://localhost:8080/v3/api-docs / http://localhost:8080/swagger-ui.html
}
