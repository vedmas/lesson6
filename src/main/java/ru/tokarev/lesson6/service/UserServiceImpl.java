package ru.tokarev.lesson6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tokarev.lesson6.repositories.User;
import ru.tokarev.lesson6.repositories.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getId(long id) {
        return (userMap.containsKey(id)) ? userMap.get(id) : getAndSave(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        if(user.getId() != null) {
            userMap.remove(user.getId());
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userMap.remove(id);
        userRepository.deleteById(id);
    }

    private User getAndSave(long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            userMap.put(id, user);
        }
        return user;
    }
}
