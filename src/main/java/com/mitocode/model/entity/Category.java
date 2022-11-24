package com.mitocode.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_category")
public class Category {
    //camelcase -> lowerCamelCase - upperCamelCase
    //db -> snake Format ___
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(length = 50, nullable = false) //name = "category_name",
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean enabled;

}
