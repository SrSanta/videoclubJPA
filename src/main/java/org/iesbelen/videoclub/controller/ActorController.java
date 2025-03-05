package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Actor;
import org.iesbelen.videoclub.service.ActorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/actores")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping({"", "/"})
    public java.util.List<Actor> all() {
        log.info("Accediendo a todos los actores");
        return this.actorService.all();
    }

    @PostMapping({"", "/"})
    public Actor newActor(@RequestBody Actor actor) {
        return this.actorService.save(actor);
    }

    @GetMapping("/{id}")
    public Actor one(@PathVariable("id") Long id) {
        return this.actorService.one(id);
    }

    @PutMapping("/{id}")
    public Actor replaceActor(@PathVariable("id") Long id, @RequestBody Actor actor) {
        return this.actorService.replace(id, actor);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteActor(@PathVariable("id") Long id) {
        this.actorService.delete(id);
    }

}
