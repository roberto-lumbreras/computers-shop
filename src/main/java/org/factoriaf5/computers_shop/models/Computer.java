package org.factoriaf5.computers_shop.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "computers")
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DECIMAL(10,2) NOT NULL")
    private BigDecimal price;
    @Column(nullable=false)
    private String brand;
    @Column(nullable=false, name = "memory_size_gigabytes")
    private Integer memorySizeGigabytes;
    @Column(nullable = false)
    private String processor;
    @Column(name = "operative_system", nullable = false)
    private String operativeSystem;
}
