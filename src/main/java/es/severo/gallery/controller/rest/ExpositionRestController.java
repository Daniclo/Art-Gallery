package es.severo.gallery.controller.rest;

import es.severo.gallery.entity.Exposition;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.service.ExpositionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expo")
@Tag(name = "Exposition API",description = "Exposition-related endpoints.")
public class ExpositionRestController {
    private ExpositionService expositionService;

    public ExpositionRestController(ExpositionService expositionService){
        this.expositionService = expositionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exposition>> getAll(){
        List<Exposition> expositions = expositionService.findAll();
        return new ResponseEntity<>(expositions, HttpStatus.OK);
    }
    @GetMapping("/all/{piece}")
    public ResponseEntity<List<Exposition>> getAllExposByPiece(@PathVariable String piece){
        List<Exposition> expositions = expositionService.getAllExposByPiece(piece);
        return new ResponseEntity<>(expositions,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Exposition> getById(@PathVariable("id") long id){
        Exposition exposition = expositionService.findById(id);
        return new ResponseEntity<>(exposition,HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Exposition> getByName(@PathVariable("name") String name){
        Exposition exposition = expositionService.findByName(name);
        return new ResponseEntity<>(exposition,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Exposition> add(@RequestBody Exposition exposition){
        Exposition newExposition = expositionService.save(exposition);
        return new ResponseEntity<>(newExposition,HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Exposition> update(@RequestBody Exposition exposition){
        try {
            Exposition updatedExposition = expositionService.update(exposition);
            return new ResponseEntity<>(updatedExposition,HttpStatus.OK);
        } catch (GalleryException e) {
            return new ResponseEntity<>(exposition,HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable Long id){
        expositionService.deleteById(id);
    }
    @DeleteMapping("/delete/name/{name}")
    public void deleteByName(@PathVariable String name){
        expositionService.deleteByName(name);
    }
}