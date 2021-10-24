package sia.taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.taco.model.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
