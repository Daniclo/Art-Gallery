package es.severo.gallery.repository;

import es.severo.gallery.entity.Piece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PieceRepository extends JpaRepository<Piece,Long> {
    @Query("select p from Piece p where p.name = :name")
    Piece findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Piece p where p.name = :name")
    int deleteByName(String name);

    @Query("select p from Piece p where p.category.name = :name")
    List<Piece> findAllByCategory(@Param("name") String name);

    @Query("select p from Piece p where p.artist.name = :name")
    List<Piece> findAllByArtist(@Param("name") String name);

    @Query("select p from Piece p inner join p.expositions expositions where expositions.name = :name")
    List<Piece> findAllByExposition(@Param("name") String name);

    @Query("select p from Piece p where p.price between ?1 and ?2 order by p.price")
    List<Piece> findByPriceBetweenOrderByPriceAsc(Double priceStart, Double priceEnd);

    @Query("select count(p) from Piece p where p.price > ?1")
    long countByPriceGreaterThan(Double price);

}