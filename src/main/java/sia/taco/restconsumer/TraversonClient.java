package sia.taco.restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;
import sia.taco.model.Ingredient;
import sia.taco.model.Taco;

import java.util.Collection;

@Component
public class TraversonClient {

    private Traverson traverson;

    @Autowired
    public TraversonClient(Traverson traverson) {
        this.traverson = traverson;
    }

    public Collection<Ingredient> getAllIngredients() {
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                new ParameterizedTypeReference<>() {
                };
        CollectionModel<Ingredient> ingredientRes =
                traverson
                        .follow("ingredients")
                        .toObject(ingredientType);
        Collection<Ingredient> ingredients = ingredientRes.getContent();
        return ingredients;
    }

    public Collection<Taco> getRecentTacos() {
        ParameterizedTypeReference<CollectionModel<Taco>> tacoType =
                new ParameterizedTypeReference<>() {
                };
        CollectionModel<Taco> tacoRes =
                traverson
                        .follow("tacos", "recents")
                        .toObject(tacoType);
        Collection<Taco> tacos = tacoRes.getContent();
        return tacos;
    }
}
