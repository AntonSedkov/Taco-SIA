package sia.taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.taco.model.TacoOrder;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

}
