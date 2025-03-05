package org.iesbelen.videoclub.repository;

import org.iesbelen.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    public List<Pelicula> findAllByOrderByTituloAsc();
}
