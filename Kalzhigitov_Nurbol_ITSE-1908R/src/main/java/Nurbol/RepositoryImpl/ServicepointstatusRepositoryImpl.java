package Nurbol.RepositoryImpl;

import Nurbol.Entities.Servicepointstatus;
import Nurbol.EntityManager;
import Nurbol.Repositories.ServicepointstatusRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class ServicepointstatusRepositoryImpl implements ServicepointstatusRepository {
    @Inject
    EntityManager em;

    @Override
    public List<Servicepointstatus> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT s FROM Servicepointstatus s");
            List<Servicepointstatus> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Servicepointstatus findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT s FROM Servicepointstatus s WHERE s.id = %d", id);
            Query query = entityManager.createQuery(sql);
            Servicepointstatus result = (Servicepointstatus) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
}
