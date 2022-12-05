package com.mitocode.controller;

import com.mitocode.exceptions.ModelNotFoundException;
import com.mitocode.model.dto.ClientDTO;
import com.mitocode.model.entity.Client;
import com.mitocode.service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    @Qualifier("clientMapper")
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> readAll() throws Exception{
        List<ClientDTO> list = clientService.findAll()
                                            .stream()
                                            .map(client -> modelMapper.map(client, ClientDTO.class))
                                            .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable("id") Integer id) throws Exception{
        ClientDTO client = modelMapper.map(clientService.findById(id), ClientDTO.class);

        if(client == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO client) throws Exception{
        Client clt = clientService.save(modelMapper.map(client, Client.class));

        return new ResponseEntity<>(modelMapper.map(clt, ClientDTO.class), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO client) throws Exception{
        Client clt = clientService.update(modelMapper.map(client, Client.class));

        return new ResponseEntity<>(modelMapper.map(clt, ClientDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Client clt = clientService.findById(id);

        if(clt == null){
            throw new ModelNotFoundException("ID Not Found: " + id);
        }

        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //API http://localhost:8080/v3/api-docs / http://localhost:8080/swagger-ui.html
}
