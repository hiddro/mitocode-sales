package com.mitocode.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
//    @JsonIncludeProperties(value = {"name"})
    private RoleDTO role;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
//    @JsonProperty(value = "user_name") // le cambia al nombre para obtenerlo o enviarlo en el json
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 60)
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // write solo para request y read pára response
    private String password;

    @NotNull
    private boolean enabled;
}
