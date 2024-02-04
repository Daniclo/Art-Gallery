package es.severo.gallery.service;

import es.severo.gallery.entity.Category;
import es.severo.gallery.exception.util.ErrorCode;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(new Category());
    }
    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
    public Category save(Category category){
        return categoryRepository.save(category);
    }
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }
    public void deleteByName(String name){
        categoryRepository.deleteByName(name);
    }
    public Category update(Category category) throws GalleryException{
        if (category.getId() != null){
            return categoryRepository.save(category);
        }
        throw new GalleryException("The ID field is mandatory", ErrorCode.IdNotFound);
    }
}