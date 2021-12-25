package Nurbol.Services;

import Nurbol.Entities.Category;
import Nurbol.Repositories.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;

@Stateful
@RunWith(JUnit4.class)
public class CategoryService {
    @Inject
    CategoryRepository categoryRepository;

    @Test
    public List<Category> getAllCategories() {return categoryRepository.findAll();}

    @Test
    public Category getCategoryById(int id) {return categoryRepository.findById(id);}

    @Test
    public Category createNewCategory(int id, String name, String description) {return categoryRepository.insertCategory(id,name,description);}

    @Test
    public Category deleteById(int id) {return categoryRepository.deleteById(id);}

    @Test
    public Category getCategoryByName(String name) {return categoryRepository.findByName(name);}
}
