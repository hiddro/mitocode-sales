package com.mitocode.config;

import com.mitocode.model.dto.ProductDTO;
import com.mitocode.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean("categoryMapper")
    public ModelMapper categoryMapper(){
        return new ModelMapper();
    }

    //en caso se llame diferente un campo del DTO referenciando a otra tabla como idCategoria
    @Bean("productMapper")
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

//        TypeMap<ProductDTO, Product> typeMap = modelMapper.createTypeMap(ProductDTO.class, Product.class);
//        typeMap.addMapping(ProductDTO::getIdCategoria, (dest, v) -> dest.getCategory().setIdCategory((Integer) v));

        return modelMapper;
    }

    @Bean("roleMapper")
    public ModelMapper roleMapper(){
        return new ModelMapper();
    }

    @Bean("clientMapper")
    public ModelMapper clientMapper(){
        return new ModelMapper();
    }

    @Bean("providerMapper")
    public ModelMapper providerMapper(){
        return new ModelMapper();
    }

    @Bean("userMapper")
    public ModelMapper userMapper(){
        return new ModelMapper();
    }

    @Bean("saleMapper")
    public ModelMapper saleMapper(){
        return new ModelMapper();
    }
}
