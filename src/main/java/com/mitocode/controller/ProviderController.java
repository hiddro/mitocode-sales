package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.ProviderDTO;
import com.mitocode.model.entity.Provider;
import com.mitocode.service.ProviderService;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    @Qualifier("providerMapper")
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception{
        List<ProviderDTO> list = providerService.findAll()
                                            .stream()
                                            .map(provider -> modelMapper.map(provider, ProviderDTO.class))
                                            .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ProviderDTO provider = modelMapper.map(providerService.findById(id), ProviderDTO.class);

        if(provider == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> save(@Valid @RequestBody ProviderDTO provider) throws Exception{
        Provider prv = providerService.save(modelMapper.map(provider, Provider.class));

        return new ResponseEntity<>(modelMapper.map(prv, ProviderDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO provider) throws Exception{
        Provider prv = providerService.update(modelMapper.map(provider, Provider.class));

        return new ResponseEntity<>(modelMapper.map(prv, ProviderDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Provider prv = providerService.findById(id);

        if(prv == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        providerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API http://localhost:8080/v3/api-docs / http://localhost:8080/swagger-ui.html
}
