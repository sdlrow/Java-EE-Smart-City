package Nurbol.Services;

import Nurbol.Entities.User;
import Nurbol.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class UserService {
    @Inject
    UserRepository userRepository;

    @Test
    public List<User> getAllUsers() {return userRepository.findAll();}

    @Test
    public User getUserById(Long id) {return userRepository.findById(id);}

    @Test
    public User authenticate(String login, String password) {return userRepository.authenticate(login, password);}

    @Test
    public User createNewUser(String login, String password) {return userRepository.createNewUser(login, password);}

    @Test
    public User getUserByLogin(String login) {return userRepository.findByLogin(login);}

    @Test
    public User deleteUserById(int id) {return userRepository.deleteById(id);}

    @Test
    public User updatePasswordById(int id, String password) {return userRepository.updatePasswordById(id, password);}

    @Test
    public User updatePasswordByLogin (String login, String password) {return userRepository.updatePasswordByLogin(login,password);}
}
