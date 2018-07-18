package my.pro.ject.service;

import my.pro.ject.domain.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    User findUser(String userId);

    void insertUser(User user1);
}
