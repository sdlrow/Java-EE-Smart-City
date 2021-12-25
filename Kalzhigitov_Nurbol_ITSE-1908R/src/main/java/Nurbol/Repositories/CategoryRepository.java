package Nurbol.Repositories;

import Nurbol.Entities.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(int id);
    Category insertCategory(int id, String name, String description);
    Category deleteById(int id);

    Category findByName(String name);
}
