package sia.taco.rest.utility;

import lombok.Getter;
import org.springframework.hateoas.EntityModel;
import sia.taco.model.Ingredient;
import sia.taco.model.Ingredient.Type;

public class IngredientEntityModel extends EntityModel<IngredientEntityModel> {
    @Getter
    private String name;
    @Getter
    private Type type;

    public IngredientEntityModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
