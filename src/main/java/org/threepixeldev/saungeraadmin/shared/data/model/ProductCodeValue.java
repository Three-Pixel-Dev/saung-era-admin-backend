package org.threepixeldev.saungeraadmin.shared.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "product_code_value")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCodeValue extends MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "code_value_id", nullable = false)
    private CodeValue codeValue;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal price;
}
