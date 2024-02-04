package es.severo.gallery.controller.rest;

import es.severo.gallery.controller.docs.ArtistApiDoc;
import es.severo.gallery.entity.Artist;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistRestController implements ArtistApiDoc {
    private final ArtistService artistService;

    public ArtistRestController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Artist>> getAll(){
        List<Artist> artists = artistService.findAll();
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    @GetMapping("/all/price")
    public ResponseEntity<List<Artist>> getAllByPrice(@RequestParam() double startPrice, @RequestParam() double endPrice){
        List<Artist> artists = artistService.findAllWithPriceHigher(startPrice,endPrice);
        return new ResponseEntity<>(artists,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Artist> getById(@PathVariable("id") long id){
        Artist artist = artistService.findById(id);
        return new ResponseEntity<>(artist,HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Artist> getByName(@PathVariable("name") String name){
        Artist artist = artistService.findByName(name);
        return new ResponseEntity<>(artist,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Artist> add(@RequestBody Artist artist){
        Artist newArtist = artistService.save(artist);
        return new ResponseEntity<>(newArtist,HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Artist> update(@RequestBody Artist artist){
        try {
            Artist updatedArtist = artistService.update(artist);
            return new ResponseEntity<>(updatedArtist,HttpStatus.OK);
        } catch (GalleryException e) {
            return new ResponseEntity<>(artist,HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable Long id){
        artistService.deleteById(id);
    }
    @DeleteMapping("/delete/name/{name}")
    public void deleteByName(@PathVariable String name){
        artistService.deleteByName(name);
    }
}