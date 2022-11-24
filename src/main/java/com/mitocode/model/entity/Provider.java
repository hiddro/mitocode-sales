package com.mitocode.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tbl_provider")
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProvider;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean enabled;
}
