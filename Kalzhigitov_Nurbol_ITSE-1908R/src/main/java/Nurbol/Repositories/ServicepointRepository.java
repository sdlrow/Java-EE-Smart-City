package Nurbol.Repositories;

import Nurbol.Entities.Servicepoint;

import java.util.List;

public interface ServicepointRepository {
    List<Servicepoint> findAll();
    Servicepoint findById(Long id);
    Servicepoint changeServicepointStatusById(int id, int status);
}
