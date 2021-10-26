package sia.taco.rest.utility;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.core.Relation;
import sia.taco.model.Taco;

import java.util.Date;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoEntityModel extends EntityModel<TacoEntityModel> {

    private static final IngredientEntityModelAssembler
            ingredientAssembler = new IngredientEntityModelAssembler();
    @Getter
    private final String name;

    @Getter
    private final Date createdAt;

    @Getter
    private CollectionModel<IngredientEntityModel> ingredients;

    public TacoEntityModel(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients =
                ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}
