package my.pro.ject.repository;

import my.pro.ject.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserId(String userId);
}
