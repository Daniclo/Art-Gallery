package es.severo.gallery.repository;

import es.severo.gallery.entity.Exposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExpositionRepository extends JpaRepository<Exposition,Long> {
    @Query("select e from Exposition e where e.name = :name")
    Exposition findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Exposition e where e.name = :name")
    int deleteByName(String name);

    @Query("select e from Exposition e inner join e.pieces pieces where pieces.name = ?1")
    List<Exposition> findByPieces_Name(String name);


}