package es.severo.gallery.service;

import es.severo.gallery.entity.Artist;
import es.severo.gallery.exception.util.ErrorCode;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public List<Artist> findAll(){
        return artistRepository.findAll();
    }
    public List<Artist> findAllWithPriceHigher(Double startPrice, Double endPrice){
        return artistRepository.findByPieces_PriceBetween(startPrice, endPrice);
    }
    public Artist findById(Long id){
        return artistRepository.findById(id).orElse(new Artist());
    }
    public Artist findByName(String name){
        return artistRepository.findByName(name);
    }
    public Artist save(Artist artist){
        return artistRepository.save(artist);
    }
    public void deleteById(Long id){
        artistRepository.deleteById(id);
    }
    public void deleteByName(String name) {
        artistRepository.deleteByName(name);
    }

    public Artist update(Artist artist) throws GalleryException {
        if (artist.getId() != null){
            return artistRepository.save(artist);
        }
        throw new GalleryException("The ID field is mandatory", ErrorCode.IdNotFound);
    }
}