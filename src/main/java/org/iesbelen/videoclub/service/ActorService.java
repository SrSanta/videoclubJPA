package org.iesbelen.videoclub.service;

import org.iesbelen.videoclub.domain.Actor;
import org.iesbelen.videoclub.exception.ActorNotFoundException;
import org.iesbelen.videoclub.repository.ActorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {
    private ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {this.actorRepository = actorRepository;}

    public List<Actor> all() {return actorRepository.findAll();}

    public Actor save(Actor actor) {return actorRepository.save(actor);}

    public Actor one(Long id) {
        return this.actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public Actor replace(Long id, Actor actor) {

        return this.actorRepository.findById(id).map( p -> (id.equals(actor.getId())  ?
                        this.actorRepository.save(actor) : null))
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    public void delete(Long id) {
        this.actorRepository.findById(id).map(p -> {this.actorRepository.delete(p);
                    return p;})
                .orElseThrow(() -> new ActorNotFoundException(id));
    }
}
