package com.mitocode.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Integer idProduct;

    @NotNull
    @Min(value = 1)
    private Integer idCategory; // si tiene el mismo nombre si funciona y lo guarda
//    private CategoryDTO category; // esto seria un objeto "category": { "id": 1 }
//    private Integer idCategoria; // en caso se llame diferente el id referenciado del DTO se corrige el ModelMapper Config

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String description;

    @Min(value = 1)
    @Max(value = 9999)
    private double price;

    @NotNull
    private boolean enabled;

}
