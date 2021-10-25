package sia.taco.data;

import org.springframework.data.repository.CrudRepository;
import sia.taco.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
