package sia.taco.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.taco.data.TacoRepository;
import sia.taco.model.Taco;
import sia.taco.rest.utility.TacoEntityModel;
import sia.taco.rest.utility.TacoEntityModelAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRController {

    private TacoRepository tacoRepository;

    @Autowired
    public DesignTacoRController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/resent")
    public CollectionModel<TacoEntityModel> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page);
        List<TacoEntityModel> tacoResources =
                new TacoEntityModelAssembler().toModels(tacos);
        CollectionModel<TacoEntityModel> recentResources =
                CollectionModel.wrap(tacoResources);
        recentResources.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                DesignTacoRController.class)
                                .recentTacos())
                        .withRel("recents"));
        return recentResources;

//        public CollectionModel<EntityModel<Taco>> recentTacos(){}
/*        CollectionModel<EntityModel<Taco>> recentResources =
                CollectionModel.wrap(tacos);

        recentResources.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                DesignTacoRController.class)
                        .recentTacos())
                .withRel("recents"));*/
 /*
        recentResources.add(
                Link.of("http://localhost:8080/design/recent", "recents"));
*/


/*
                WebMvcLinkBuilder.linkTo(DesignTacoRController.class)
                        .slash("recent")
                        .withRel("recents"));
*/

//        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

}
