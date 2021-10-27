package sia.taco.restconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sia.taco.model.Ingredient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class Getter {
    private RestTemplate restTemplate;

    @Autowired
    public Getter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject(
                "http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId
        );
    }

    public Ingredient getIngredientByIdSecond(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return restTemplate.getForObject(
                "http://localhost:8080/ingredients/{id}",
                Ingredient.class, urlVariables);
    }

    public Ingredient getIngredientByIdThird(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/ingredients/{id}")
                .build(urlVariables);
        return restTemplate.getForObject(url, Ingredient.class);
    }

    public Ingredient getIngredientByIdResponseEntity(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.getForEntity(
                        "http://localhost:8080/ingredients/{id}",
                        Ingredient.class, ingredientId);
        log.info("Fetched time: " +
                responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }


    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}",
                ingredient, ingredient.getId());
    }


    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}",
                ingredient.getId());
    }


    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                "http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }

    public URI createIngredientUri(Ingredient ingredient) {
        return restTemplate.postForLocation(
                "http://localhost:8080/ingredients",
                ingredient);
    }

    public Ingredient createIngredientResponseEntity(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.postForEntity(
                        "http://localhost:8080/ingredients",
                        ingredient,
                        Ingredient.class);
        log.info("New resource created at " +
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

}
