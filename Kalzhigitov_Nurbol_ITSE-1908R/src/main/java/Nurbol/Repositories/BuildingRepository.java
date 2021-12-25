package Nurbol.Repositories;

import Nurbol.Entities.Building;

import java.time.LocalDate;
import java.util.List;

public interface BuildingRepository {
    List<Building> findAll();
    Building findById(int id);
    List<Building> findByCategory(int id);
    Building findByName(String name);
    Building deleteById(int id);
    Building updateRatingById(int id, double rating);
    Building updateDescriptionById(int id, String description);
    Building updateAddressById(int id, String address);

    List<Building> getAllBuildingsByCategoryName(String name);

    Building updateNameById(int id, String name);
    Building insertNewBuilding(int id, String name, LocalDate date, double rating, String address, String phoneNumber, String description);
}
