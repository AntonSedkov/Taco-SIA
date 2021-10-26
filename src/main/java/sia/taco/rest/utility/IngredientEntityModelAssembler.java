package sia.taco.rest.utility;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import sia.taco.model.Ingredient;
import sia.taco.rest.IngredientRController;

public class IngredientEntityModelAssembler extends
        RepresentationModelAssemblerSupport<Ingredient, IngredientEntityModel> {

    public IngredientEntityModelAssembler() {
        super(IngredientRController.class, IngredientEntityModel.class);
    }

    @Override
    public IngredientEntityModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
    @Override
    protected IngredientEntityModel instantiateModel(Ingredient ingredient) {
        return new IngredientEntityModel(ingredient);
    }

}
