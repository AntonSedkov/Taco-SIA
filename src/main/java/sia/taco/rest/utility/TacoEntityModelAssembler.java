package sia.taco.rest.utility;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import sia.taco.model.Taco;
import sia.taco.rest.DesignTacoRController;

import java.util.ArrayList;
import java.util.List;

public class TacoEntityModelAssembler
        extends RepresentationModelAssemblerSupport<Taco, TacoEntityModel> {

    public TacoEntityModelAssembler() {
        super(DesignTacoRController.class, TacoEntityModel.class);
    }

    @Override
    protected TacoEntityModel instantiateModel(Taco taco) {
        return new TacoEntityModel(taco);
    }

    @Override
    public TacoEntityModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }

    public List<TacoEntityModel> toModels(List<Taco> tacos) {
        List<TacoEntityModel> models = new ArrayList<>();
        for (Taco taco : tacos) {
            TacoEntityModel model = toModel(taco);
            models.add(model);
        }
        return models;
    }

}
