package Nurbol.Repositories;

import Nurbol.Entities.BuildingsCategory;

import java.util.List;

public interface BuildingsCategoryRepository {
    List<BuildingsCategory> findAll();
    BuildingsCategory findById(Long id);
    BuildingsCategory deleteById(int id);

}
