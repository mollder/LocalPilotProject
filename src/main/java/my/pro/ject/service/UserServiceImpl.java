package my.pro.ject.service;

import my.pro.ject.domain.User;
import my.pro.ject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(String userId) {
        return userRepository.findUserByUserId(userId);
    }

    @Override
    public void insertUser(User user) {
        userRepository.save(user);
    }
}
