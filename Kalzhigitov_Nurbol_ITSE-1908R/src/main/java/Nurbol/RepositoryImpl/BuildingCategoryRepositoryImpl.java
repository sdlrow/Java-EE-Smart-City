package Nurbol.RepositoryImpl;

import Nurbol.Entities.BuildingsCategory;
import Nurbol.Repositories.BuildingsCategoryRepository;
import Nurbol.EntityManager;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class BuildingCategoryRepositoryImpl implements BuildingsCategoryRepository {
    @Inject
    EntityManager em;


    @Override
    public List<BuildingsCategory> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT b FROM BuildingsCategory b");
            List<BuildingsCategory> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public BuildingsCategory findById(Long id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT b FROM BuildingsCategory b WHERE b.id = %d", id);
            Query query = entityManager.createQuery(sql);
            BuildingsCategory result = (BuildingsCategory) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public BuildingsCategory deleteById(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            BuildingsCategory buildingsCategory = entityManager.find(BuildingsCategory.class, id);
            entityManager.remove(buildingsCategory);
            return buildingsCategory;
        } catch (NoResultException e){
            return null;
        }
    }
}
