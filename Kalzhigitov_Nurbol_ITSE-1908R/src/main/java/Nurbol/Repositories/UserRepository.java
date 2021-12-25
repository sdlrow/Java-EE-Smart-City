package Nurbol.Repositories;

import Nurbol.Entities.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Long id);
    User authenticate(String login, String password);
    User deleteById(int id);
    User updatePasswordById(int id, String password);
    User updatePasswordByLogin(String login, String password);
    User createNewUser(String login, String password);

    User findByLogin(String login);
}
