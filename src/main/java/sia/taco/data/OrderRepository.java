package sia.taco.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import sia.taco.model.TacoOrder;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    @PreAuthorize("hasRole('ADMIN')")
    void deleteAll();
}
