package sia.taco.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import sia.taco.model.TacoOrder;
import sia.taco.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteAll();
}
