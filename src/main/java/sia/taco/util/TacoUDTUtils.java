package sia.taco.util;

import sia.taco.model.Ingredient;
import sia.taco.model.IngredientUDT;

public class TacoUDTUtils {
    private TacoUDTUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
