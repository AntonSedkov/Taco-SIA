package sia.taco.restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sia.taco.model.Ingredient;

@Component
public class RestTemplateAndTraversonTogether {

    private RestTemplate restTemplate;
    private Traverson traverson;

    @Autowired
    public RestTemplateAndTraversonTogether(RestTemplate restTemplate, Traverson traverson) {
        this.restTemplate = restTemplate;
        this.traverson = traverson;
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson
                .follow("ingredients")
                .asLink()
                .getHref();
        return restTemplate.postForObject(
                ingredientsUrl,
                ingredient,
                Ingredient.class);
    }
}
