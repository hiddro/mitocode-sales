package com.mitocode.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_role")
public class Role {

    @Id
    @EqualsAndHashCode.Include
    private Integer idRole;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;


}
