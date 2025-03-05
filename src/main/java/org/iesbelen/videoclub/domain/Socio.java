package org.iesbelen.videoclub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="socio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    private String dni;
    private String nombre;
    private String apellidos;

    @ElementCollection
    @CollectionTable(name = "telefonos_socio", joinColumns = @JoinColumn(name = "socio_id"))
    @Column(name = "telefono")
    private Set<String> telefonos;

    @ElementCollection
    @CollectionTable(name = "direcciones_socio", joinColumns = @JoinColumn(name = "socio_id"))
    @AttributeOverrides({
            @AttributeOverride(name ="calle", column = @Column(name = "calle")),
            @AttributeOverride(name ="numero", column = @Column(name = "numero")),
            @AttributeOverride(name ="ciudad", column = @Column(name = "ciudad")),
            @AttributeOverride(name ="cp", column = @Column(name = "cp"))
    })
    private Set<Direccion> direccionesSecundarias = new HashSet<>();

    @Embedded
    private Direccion direccionPrincipal;

    @OneToOne
    @JoinColumn(name = "tarjeta_id", referencedColumnName = "id")
    private Tarjeta tarjeta;
}
