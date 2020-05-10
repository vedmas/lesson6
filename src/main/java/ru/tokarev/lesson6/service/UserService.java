package ru.tokarev.lesson6.service;

import ru.tokarev.lesson6.repositories.User;

import java.util.List;

public interface UserService {

    User getId(long id);

    List<User> getAll();

    void save(User user);

    void delete(long id);
}
