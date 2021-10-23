package sia.taco.data;

import sia.taco.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
