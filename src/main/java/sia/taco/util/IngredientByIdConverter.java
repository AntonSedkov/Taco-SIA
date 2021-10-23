package sia.taco.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.taco.data.IngredientRepository;
import sia.taco.model.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository repository;

    @Autowired
    public IngredientByIdConverter(IngredientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ingredient convert(String id) {
        return repository.findById(id).orElse(null);
    }
}
