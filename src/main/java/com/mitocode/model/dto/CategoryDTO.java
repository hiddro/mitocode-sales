package com.mitocode.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50) // en numeros se usa el Max() y Min()
    private String nameCategory;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    private String descriptionCategory;

    @NotNull
    private boolean enabledCategory;
}
