package Nurbol.RepositoryImpl;

import Nurbol.Entities.Building;
import Nurbol.EntityManager;
import Nurbol.Repositories.BuildingRepository;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class BuildingRepositoryImpl implements BuildingRepository {

    @Inject
    EntityManager em;


    @Override
    public List<Building> findAll() {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Query query = entityManager.createQuery("SELECT b from Building b");
            List<Building> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Building findById(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT b FROM Building b WHERE b.id = %d", id);
            Query query = entityManager.createQuery(sql);
            Building result = (Building) query.getSingleResult();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }
//"SELECT b FROM Building b, Category c, BuildingsCategory bc  WHERE b.id = bc.building AND bc.category = %d"
    @Override
    public List<Building> findByCategory(int id) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT b FROM Building b WHERE b.id in (SELECT bc.building.id FROM BuildingsCategory bc where bc.category.id = %d)", id);
            Query query = entityManager.createQuery(sql);
            List<Building> result = query.getResultList();
            return result;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Building findByName(String name) {
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT b FROM Building b WHERE b.name = '%s'", name);
            Query query = entityManager.createQuery(sql);
            Building result = (Building) query.getSingleResult();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Building deleteById(int id){
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Building building = entityManager.find(Building.class, id);
            entityManager.remove(building);
            return building;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Building updateRatingById(int id, double rating){
        javax.persistence.EntityManager entityManager = em.manager();
        Building building = entityManager.find(Building.class, id);
        building.setRating(rating);
        entityManager.persist(building);
        return building;
    }

    @Override
    public Building updateDescriptionById(int id, String description){
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Building building = entityManager.find(Building.class, id);
            building.setDescription(description);
            entityManager.persist(building);
            return building;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Building updateAddressById(int id, String address){
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Building building = entityManager.find(Building.class, id);
            building.setAddress(address);
            entityManager.persist(building);
            return building;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Building> getAllBuildingsByCategoryName(String name){
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            String sql = String.format("SELECT b FROM Building b WHERE b.id in (SELECT bc.building.id FROM BuildingsCategory bc where bc.category.name = '%s')", name);
            Query query = entityManager.createQuery(sql);
            List<Building> result = query.getResultList();
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Building updateNameById(int id, String name){
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Building building = entityManager.find(Building.class, id);
            building.setName(name);
            entityManager.persist(building);
            return building;
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Building insertNewBuilding(int id, String name, LocalDate date, double rating, String address, String phoneNumber, String description){
        javax.persistence.EntityManager entityManager = em.manager();
        try {
            Building building = new Building(id, name, date, rating, address, phoneNumber, description);
            entityManager.persist(building);
            return building;
        } catch (NoResultException e){
            return null;
        }
    }
}
