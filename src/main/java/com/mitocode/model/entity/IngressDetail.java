package com.mitocode.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_ingress_detail")
@IdClass(IngressDetailPk.class)
public class IngressDetail {

    @Id
    private Ingress ingress;

    @Id
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private short cost;
}
