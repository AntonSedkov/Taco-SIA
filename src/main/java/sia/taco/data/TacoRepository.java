package sia.taco.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.taco.model.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {

    Iterable<Taco> findAll(Pageable pageable);

}
