package org.iesbelen.videoclub.domain;

import jakarta.persistence.*;

@Embeddable
public class Direccion {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "direccion_id")
    private int id;
    private String calle;
    private int numero;
    private String ciudad;
    private int cp;
}
