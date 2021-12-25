package Nurbol.RepositoryImpl;

import Nurbol.Entities.Servicepoint;
import Nurbol.Entities.Servicepointstatus;
import Nurbol.EntityManager;
import Nurbol.Repositories.ServicepointRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class ServicepointRepositoryImpl implements ServicepointRepository {
    @Inject
    EntityManager em;

    @Override
    public List<Servicepoint> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT s FROM Servicepoint s");
            List<Servicepoint> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Servicepoint findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT s FROM Servicepoint s WHERE s.id = %d", id);
            Query query = entityManager.createQuery(sql);
            Servicepoint result = (Servicepoint) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Servicepoint changeServicepointStatusById(int id, int status) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Servicepoint servicepoint = entityManager.find(Servicepoint.class, id);
            Servicepointstatus servicepointstatus = entityManager.find(Servicepointstatus.class, servicepoint.getStatus().getId());
            servicepoint.setStatus(servicepointstatus);
            entityManager.persist(servicepoint);
            return servicepoint;
        } catch (NoResultException e){
            return null;
        }
    }
}
