package es.severo.gallery.service;

import es.severo.gallery.controller.util.PieceFieldSort;
import es.severo.gallery.entity.Piece;
import es.severo.gallery.exception.util.ErrorCode;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.repository.PieceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceService {
    private final PieceRepository pieceRepository;

    public PieceService(PieceRepository pieceRepository){
        this.pieceRepository = pieceRepository;
    }

    public List<Piece> findAll(){
        return pieceRepository.findAll();
    }
    public List<Piece> findAlPaged(Integer pageNo, Integer pageSize, PieceFieldSort sortBy, Sort.Direction orderBy){
        return pieceRepository.findAll(PageRequest.of(pageNo-1,pageSize,
                Sort.by(orderBy, sortBy.getField()))).getContent();
    }
    public List<Piece> findAllByCategory(String category){
        return pieceRepository.findAllByCategory(category);
    }
    public List<Piece> findAllByArtist(String artist){
        return pieceRepository.findAllByArtist(artist);
    }
    public List<Piece> findAllByExposition(String exposition){
        return pieceRepository.findAllByExposition(exposition);
    }
    public List<Piece> findAllByPriceOrdered(Double startPrice, Double endPrice){
        return pieceRepository.findByPriceBetweenOrderByPriceAsc(startPrice, endPrice);
    }
    public Piece findById(Long id){
        return pieceRepository.findById(id).orElse(new Piece());
    }
    public Piece findByName(String name){
        return pieceRepository.findByName(name);
    }
    public Long countByPrice(Double price){
        return pieceRepository.countByPriceGreaterThan(price);
    }
    public Piece save(Piece piece){
        return pieceRepository.save(piece);
    }
    public Piece update(Piece piece) throws GalleryException {
        if (piece.getId() != null){
            return pieceRepository.save(piece);
        }
        throw new GalleryException("The ID field is mandatory", ErrorCode.IdNotFound);
    }
    public void deleteById(Long id){
        pieceRepository.deleteById(id);
    }
    public void deleteByName(String name){
        pieceRepository.deleteByName(name);
    }

    public List<Piece> addList(List<Piece> pieces) {
        return pieceRepository.saveAll(pieces);
    }
}