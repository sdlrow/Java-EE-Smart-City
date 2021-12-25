package Nurbol.Services;

import Nurbol.Entities.Userdetail;
import Nurbol.Repositories.UserdetailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class UserdetailService {
    @Inject
    UserdetailRepository userdetailRepository;

    @Test
    public List<Userdetail> getAllUserdetails() {return userdetailRepository.findAll();}

    @Test
    public Userdetail getUserdetailById(Long id) {return userdetailRepository.findById(id);}
}
