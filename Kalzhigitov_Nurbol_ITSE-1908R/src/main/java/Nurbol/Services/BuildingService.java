package Nurbol.Services;

import Nurbol.Entities.Building;
import Nurbol.Repositories.BuildingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class BuildingService {

    @Inject
    BuildingRepository buildingRepository;

    @Test
    public List<Building> getAllBuildings() {return buildingRepository.findAll();}

    @Test
    public Building getBuildingById(int id) {return buildingRepository.findById(id);}

    @Test
    public List<Building> getBuildingsByCategory(int id) {return buildingRepository.findByCategory(id);}

    @Test
    public Building getBuildingByName(String name) {return buildingRepository.findByName(name);}

    @Test
    public Building updateRatingById(int id, double rating) {return buildingRepository.updateRatingById(id, rating);}

    @Test
    public Building updateDescriptionById(int id, String description) {return buildingRepository.updateDescriptionById(id, description);}

    @Test
    public Building updateAddressById(int id, String address) {return buildingRepository.updateAddressById(id, address);}

    @Test
    public Building updateNameById(int id, String name) {return buildingRepository.updateNameById(id, name);}

    @Test
    public Building insertNewBuilding(int id, String name, LocalDate date, double rating, String address, String phoneNumber, String description) {return buildingRepository.insertNewBuilding(id, name, date, rating, address, phoneNumber, description);}

    @Test
    public Building deleteBuildingById(int id) {return buildingRepository.deleteById(id);}

    @Test
    public List<Building> getAllBuildingsByCategoryName(String name) {return buildingRepository.getAllBuildingsByCategoryName(name);}
}
