package sia.taco.reporest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import sia.taco.data.TacoRepository;
import sia.taco.model.Taco;
import sia.taco.rest.utility.TacoEntityModel;
import sia.taco.rest.utility.TacoEntityModelAssembler;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacoController {

    private TacoRepository tacoRepository;

    @Autowired
    public RecentTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/ recent", produces = "application/hal+json")
    public ResponseEntity<CollectionModel<TacoEntityModel>> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepository.findAll(page);
        CollectionModel<TacoEntityModel> tacoResources =
                new TacoEntityModelAssembler().toCollectionModel(tacos);
        CollectionModel<TacoEntityModel> recentResources =
                CollectionModel.wrap(tacoResources);
        recentResources.add(
                linkTo(methodOn(RecentTacoController.class).recentTacos())
                        .withRel("recents"));
        return new ResponseEntity<>(recentResources, HttpStatus.OK);
    }
}
