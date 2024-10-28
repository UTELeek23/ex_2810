package hisc.com.spring_2810.service;
import hisc.com.spring_2810.repository.CategoryRepository;
import hisc.com.spring_2810.Entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Page<Category> search(String name, Pageable pageable) {
        return categoryRepository.findByNameContaining(name, pageable);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
