package com.mitocode.model.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class IngressDetailPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_ingress", nullable = false, foreignKey = @ForeignKey(name = "Fk_IngressDetail_Ingress"))
    private Ingress ingress;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "Fk_IngressDetail_Product"))
    private Product product;
}
