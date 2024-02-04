package es.severo.gallery.repository;

import es.severo.gallery.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
    @Query("select a from Artist a where a.name = :name")
    Artist findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Artist a where a.name = :name")
    int deleteByName(String name);

    @Query("select a from Artist a inner join a.pieces pieces where pieces.price between ?1 and ?2")
    List<Artist> findByPieces_PriceBetween(Double priceStart, Double priceEnd);

}