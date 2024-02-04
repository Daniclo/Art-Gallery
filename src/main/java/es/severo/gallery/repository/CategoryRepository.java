package es.severo.gallery.repository;

import es.severo.gallery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.name = :name")
    Category findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Category c where c.name = :name")
    int deleteByName(String name);

}
