package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Categoria;
import org.iesbelen.videoclub.exception.PeliculaNotFoundException;
import org.iesbelen.videoclub.repository.PeliculaCustomRepository;
import org.iesbelen.videoclub.repository.PeliculaRepository;
import org.iesbelen.videoclub.domain.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaCustomRepository peliculaCustomRepository;

    @Autowired
    private CategoriaService categoriaService;

    public PeliculaService(PeliculaRepository peliculaRepository) {

        this.peliculaRepository = peliculaRepository;
    }

    public List<Pelicula> all() {
        return this.peliculaRepository.findAll();
    }

    public Pelicula save(Pelicula pelicula) {
        return this.peliculaRepository.save(pelicula);
    }

    public Pelicula one(Long id) {
        return this.peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public Pelicula replace(Long id, Pelicula pelicula) {

        return this.peliculaRepository.findById(id).map( p -> (id.equals(pelicula.getIdPelicula())  ?
                                                            this.peliculaRepository.save(pelicula) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public void delete(Long id) {
        this.peliculaRepository.findById(id).map(p -> {this.peliculaRepository.delete(p);
                                                        return p;})
                .orElseThrow(() -> new PeliculaNotFoundException(id));
    }

    public List<Pelicula> findAllByOrderByTituloAsc() {
        return this.peliculaRepository.findAllByOrderByTituloAsc();
    }

    public List<Pelicula> findAllByQueryFilters(Optional<String> buscarOptional, Optional<String> ordenarOptional) {
        return this.peliculaCustomRepository.queryCustomPelicula(buscarOptional, ordenarOptional);
    }

    public Pelicula addCategoria(Long id, Long idCategoria) {
        Pelicula pelicula = one(id);
        Categoria categoria = categoriaService.one(idCategoria);

        pelicula.getCategorias().add(categoria);
        categoria.getPeliculas().add(pelicula);

        return save(pelicula);
    }

    public Map<String, Object> all(int pagina, int tamanio) {
        Pageable paginado = PageRequest.of(pagina, tamanio, Sort.by("idPelicula").ascending());

        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

//    public List<Pelicula> findAllOrderByCol(String[] orden) {
//        return this.peliculaCustomRepository.pelisOrderbyCols(Optional.of(orden));
//    }

    public Map<String, Object> all(String[] paginacion) {
        Pageable paginado = PageRequest.of(Integer.parseInt(paginacion[0]),Integer.parseInt(paginacion[1]), Sort.by("idPelicula").ascending());

        Page<Pelicula> pageAll = this.peliculaRepository.findAll(paginado);

        Map<String, Object> response = new HashMap<>();

        response.put("peliculas", pageAll.getContent());
        response.put("currentPage", pageAll.getNumber());
        response.put("totalItems", pageAll.getTotalElements());
        response.put("totalPages", pageAll.getTotalPages());

        return response;
    }

//    public List<Pelicula> allbyColumn(String[] orden) {
//        return peliculaCustomRepository.pelisOrdenadabyColSentido(Optional.of(orden));
//    }
    public List<Pelicula> allbyColumn2(String[] orden) {
        Sort sort = null;
        if (orden != null && orden.length == 2) {
            String columna = orden[0];
            String sentido = orden[1];
            if("asc".equalsIgnoreCase(sentido)){
                sort = Sort.by(columna).ascending();
            } else {
                sort = Sort.by(columna).descending();
            }
        }
        return peliculaRepository.findAll(sort);
    }
}
