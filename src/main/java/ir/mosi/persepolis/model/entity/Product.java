package ir.mosi.persepolis.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
    @SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    //شناسه

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    //نام محصول

}
