package Nurbol.Services;

import Nurbol.Entities.Servicepoint;
import Nurbol.Repositories.ServicepointRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class ServicepointService {
    @Inject
    ServicepointRepository servicepointRepository;

    @Test
    public List<Servicepoint> getAllServicepoints() {return servicepointRepository.findAll();}

    @Test
    public Servicepoint getServicepointById(Long id) {return servicepointRepository.findById(id);}

    @Test
    public Servicepoint updateStatus(int id, int status) {return servicepointRepository.changeServicepointStatusById(id, status);}
}
