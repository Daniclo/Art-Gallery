package es.severo.gallery.controller.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import es.severo.gallery.controller.docs.PieceApiDoc;
import es.severo.gallery.controller.util.PieceFieldSort;
import es.severo.gallery.entity.Piece;
import es.severo.gallery.exception.GalleryException;
import es.severo.gallery.service.PieceService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
@RequestMapping("/piece")
public class PieceRestController implements PieceApiDoc {
    private PieceService pieceService;

    public PieceRestController(PieceService pieceService){
        this.pieceService = pieceService;
    }

    @GetMapping("/json")
    public ResponseEntity<String> saveInfoByArtist(@RequestParam() String name){
        List<Piece> pieces = pieceService.findAllByArtist(name);
        Gson gson = new Gson();
        JsonElement json = JsonParser.parseString(gson.toJson(pieces.toString()));
        Path p = Path.of("./files/data.json");
        try {
            Files.writeString(p,json.toString(), StandardOpenOption.CREATE);
            return new ResponseEntity<>(p.toFile().getAbsolutePath(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Piece>> getAll(){
        List<Piece> pieces = pieceService.findAll();
        return new ResponseEntity<>(pieces, HttpStatus.OK);
    }
    @GetMapping("/all/paged")
    public ResponseEntity<List<Piece>> getAllPaged(@RequestParam(defaultValue = "1") Integer pageNo,
                                                   @RequestParam(defaultValue = "1") Integer pageSize,
                                                   @RequestParam(required = false)PieceFieldSort sortBy,
                                                   @RequestParam(required = false, defaultValue = "ASC")Sort.Direction orderBy){

        List<Piece> pieces = pieceService.findAlPaged(pageNo,pageSize,sortBy,orderBy);
        return new ResponseEntity<>(pieces,HttpStatus.OK);
    }

    @GetMapping("/all/cat")
    public ResponseEntity<List<Piece>> getAllByCategory(@RequestParam() String name){
        List<Piece> pieces = pieceService.findAllByCategory(name);
        return new ResponseEntity<>(pieces,HttpStatus.OK);
    }
    @GetMapping("/all/artist")
    public ResponseEntity<List<Piece>> getAllByArtist(@RequestParam() String name){
        List<Piece> pieces = pieceService.findAllByArtist(name);
        return new ResponseEntity<>(pieces,HttpStatus.OK);
    }
    @GetMapping("/all/expo")
    public ResponseEntity<List<Piece>> getAllByExposition(@RequestParam() String name){
        List<Piece> pieces = pieceService.findAllByExposition(name);
        return new ResponseEntity<>(pieces,HttpStatus.OK);
    }
    @GetMapping("/all/price")
    public ResponseEntity<List<Piece>> getAllPiecesByPrice(@RequestParam() Double startPrice, @RequestParam() Double endPrice){
        List<Piece> pieces = pieceService.findAllByPriceOrdered(startPrice, endPrice);
        return new ResponseEntity<>(pieces,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Piece> getById(@PathVariable("id") long id){
        Piece piece = pieceService.findById(id);
        return new ResponseEntity<>(piece,HttpStatus.OK);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Piece> getByName(@PathVariable("name") String name){
        Piece piece = pieceService.findByName(name);
        return new ResponseEntity<>(piece,HttpStatus.OK);
    }
    @GetMapping("/count/{price}")
    public ResponseEntity<Long> getSumOfPiecesByPrice(@PathVariable("price") Double price){
        Long count = pieceService.countByPrice(price);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }
    @PostMapping("/addList")
    public ResponseEntity<List<Piece>> addList(@RequestBody List<Piece> pieces){
        List<Piece> addedPieces = pieceService.addList(pieces);
        return new ResponseEntity<>(addedPieces,HttpStatus.CREATED);
    }
    @PostMapping("/add")
    public ResponseEntity<Piece> add(@RequestBody Piece piece){
        Piece newPiece = pieceService.save(piece);
        return new ResponseEntity<>(newPiece,HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Piece> update(@RequestBody Piece piece){
        try {
            Piece updatedPiece = pieceService.update(piece);
            return new ResponseEntity<>(updatedPiece,HttpStatus.OK);
        } catch (GalleryException e) {
            return new ResponseEntity<>(piece,HttpStatus.NOT_MODIFIED);
        }
    }
    @DeleteMapping("/delete/id/{id}")
    public void deleteById(@PathVariable Long id){
        pieceService.deleteById(id);
    }
    @DeleteMapping("/delete/name/{name}")
    public void deleteByName(@PathVariable String name){
        pieceService.deleteByName(name);
    }
}