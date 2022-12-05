package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.SaleDTO;
import com.mitocode.model.entity.Sale;
import com.mitocode.service.SaleService;
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
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    @Qualifier("saleMapper")
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception{
        List<SaleDTO> list = saleService.findAll()
                                            .stream()
                                            .map(sale -> modelMapper.map(sale, SaleDTO.class))
                                            .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> findById(@PathVariable("id") Integer id) throws Exception{
        SaleDTO sale = modelMapper.map(saleService.findById(id), SaleDTO.class);

        if(sale == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> save(@Valid @RequestBody SaleDTO sale) throws Exception{
        Sale sle = saleService.save(modelMapper.map(sale, Sale.class));

        return new ResponseEntity<>(modelMapper.map(sle, SaleDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO sale) throws Exception{
        Sale sle = saleService.update(modelMapper.map(sale, Sale.class));

        return new ResponseEntity<>(modelMapper.map(sle, SaleDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Sale sle = saleService.findById(id);

        if(sle == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        saleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API http://localhost:8080/v3/api-docs / http://localhost:8080/swagger-ui.html
}
