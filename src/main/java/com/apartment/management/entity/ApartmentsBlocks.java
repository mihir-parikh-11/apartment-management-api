package com.apartment.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * An ApartmentsBlocks Entity.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "apartments_blocks")
public class ApartmentsBlocks implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "block_name", nullable = false)
    private String blockName;

    @Column(name = "number_of_flats", nullable = false)
    private Integer numberOfFlats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartments_id")
    private Apartments apartments;

    @Override
    public String toString() {
        return "ApartmentsBlocks{" +
                "id=" + id +
                ", blockName='" + blockName + '\'' +
                ", numberOfFlats=" + numberOfFlats +
                '}';
    }
}
