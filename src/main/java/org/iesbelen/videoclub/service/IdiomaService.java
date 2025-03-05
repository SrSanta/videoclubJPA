package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Idioma;
import org.iesbelen.videoclub.exception.IdiomaNotFoundException;
import org.iesbelen.videoclub.exception.PeliculaNotFoundException;
import org.iesbelen.videoclub.repository.IdiomaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdiomaService {
    private final IdiomaRepository idiomaRepository;
    public IdiomaService(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }

    public List<Idioma> all() {
        return this.idiomaRepository.findAll();
    }

    public Idioma one(Long id){
        return this.idiomaRepository.findById(id)
                .orElseThrow(() -> new IdiomaNotFoundException(id));
    }

    public Idioma save(Idioma idioma) {
        return this.idiomaRepository.save(idioma);
    }

    public Idioma replace(Long id, Idioma idioma) {
        return this.idiomaRepository.findById(id).map( p -> (id.equals(idioma.getId())  ?
                        this.idiomaRepository.save(idioma) : null))
                .orElseThrow(() -> new IdiomaNotFoundException(id));
    }

    public void delete(Long id) {
        this.idiomaRepository.findById(id).map(p -> {this.idiomaRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new IdiomaNotFoundException(id));
    }
}
