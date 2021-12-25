package Nurbol.Services;

import Nurbol.Entities.Servicepointstatus;
import Nurbol.Repositories.ServicepointstatusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class ServicepointstatusService {
    @Inject
    ServicepointstatusRepository servicepointstatusRepository;

    @Test
    public List<Servicepointstatus> getAllServicepointStatuses() {return servicepointstatusRepository.findAll();}

    @Test
    public Servicepointstatus getServicepointStatsById(Long id) {return servicepointstatusRepository.findById(id);}
}
