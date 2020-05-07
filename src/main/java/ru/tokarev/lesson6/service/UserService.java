package ru.tokarev.lesson6.service;

import ru.tokarev.lesson6.repositories.User;

public interface UserService {

    User getId(long id);

    void save(User user);

    void delete(long id);
}
