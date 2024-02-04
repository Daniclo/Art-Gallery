package es.severo.gallery.controller.rest;

import es.severo.gallery.entity.Category;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
@Tag(name = "Category API",description = "Category-related endpoints.")
public class CategoryRestController {
    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") long id){
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getByName(@PathVariable("name") String name){
        Category category = categoryService.findByName(name);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category){
        Category newCategory = categoryService.save(category);
        return new ResponseEntity<>(newCategory,HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category){
        try {
            Category updatedCategory = categoryService.update(category);
            return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
        } catch (GalleryException e) {
            return new ResponseEntity<>(category,HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable Long id){
        categoryService.deleteById(id);
    }
    @DeleteMapping("/delete/name/{name}")
    public void deleteByName(@PathVariable String name){
        categoryService.deleteByName(name);
    }
}