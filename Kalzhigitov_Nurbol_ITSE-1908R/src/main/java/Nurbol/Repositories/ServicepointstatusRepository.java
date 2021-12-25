package Nurbol.Repositories;

import Nurbol.Entities.Servicepointstatus;

import java.util.List;

public interface ServicepointstatusRepository {
    List<Servicepointstatus> findAll();
    Servicepointstatus findById(Long id);
}
