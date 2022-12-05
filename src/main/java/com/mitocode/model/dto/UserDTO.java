package com.mitocode.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mitocode.model.entity.Role;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @NotNull
    private Integer idUser;

    @NotNull
    private RoleDTO role;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 60)
    private String password;

    @NotNull
    private boolean enabled;
}
