package es.severo.gallery.controller.docs;

import es.severo.gallery.controller.util.PieceFieldSort;
import es.severo.gallery.entity.Piece;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Piece API", description = "Piece-related endpoints.")
public interface PieceApiDoc {
    @Operation(
            summary = "Saves all pieces made by a given artist.",
            description = "Saves the complete info for all pieces made by a given artist into your computer in the form" +
                    "of a .json file. Returns the path where the file is created.",
            tags = {"json","piece","artist","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<String> saveInfoByArtist(@RequestParam() String name);

    @Operation(
            summary = "Retrieve all pieces.",
            description = "Get all pieces from the database. Returns them in a list.",
            tags = {"get","piece","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> getAll();

    @Operation(
            summary = "Retrieves all pieces paged,",
            description = "Get all pieces from the database. Returns them in a paged list",
            tags = {"get","piece","list","paged"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> getAllPaged(@RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "1") Integer pageSize,
                                            @RequestParam(required = false) PieceFieldSort sortBy,
                                            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction orderBy);


    @Operation(
            summary = "Retrieves all pieces of a given category.",
            description = "Get all pieces from the database that belong to a given category. Returns them in a list.",
            tags = {"get","piece","category","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> getAllByCategory(@RequestParam() String name);

    @Operation(
            summary = "Retrieves all pieces made by a given artist.",
            description = "Get all pieces from the database that were made by a given artist. Returns them in a list.",
            tags = {"get","piece","artist","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> getAllByArtist(@RequestParam() String name);

    @Operation(
            summary = "Retrieves all pieces present in a given exposition.",
            description = "Get all pieces from the database that are present in a given exposition. Returns them in a list.",
            tags = {"get","piece","exposition","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> getAllByExposition(@RequestParam() String name);

    @Operation(
            summary = "Retrieves all pieces between 2 given price values.",
            description = "Get all pieces from the database which price is in between 2 given values. Returns them in a list.",
            tags = {"get","piece","price","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> getAllPiecesByPrice(@RequestParam() Double startPrice, @RequestParam() Double endPrice);

    @Operation(
            summary = "Retrieves a piece by a given ID",
            description = "Get a piece from the database by a given ID. Returns a piece instance.",
            tags = {"get","piece"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Piece> getById(@PathVariable("id") long id);

    @Operation(
            summary = "Retrieves a piece by a given name",
            description = "Get a piece from the database by a given name. Returns a piece instance.",
            tags = {"get","piece"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Piece> getByName(@PathVariable("name") String name);

    @Operation(
            summary = "Retrieves the number of pieces with a price over a given value.",
            description = "Get the number of pieces in the database with a price over a given double value." +
                    "Returns a long instance.",
            tags = {"get","piece","long","count"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Long> getSumOfPiecesByPrice(@PathVariable("price") Double price);

    @Operation(
            summary = "Adds a new list of pieces to the database.",
            description = "Creates new pieces from a list and adds them to the database. Returns the new pieces if created correctly.",
            tags = {"post","piece","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Piece>> addList(@RequestBody List<Piece> pieces);

    @Operation(
            summary = "Adds a new piece to the database.",
            description = "Creates a new piece and adds it to the database. Returns the new piece if created correctly.",
            tags = {"post","piece","create"}
    )@ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Piece> add(@RequestBody Piece piece);

    @Operation(
            summary = "Updates an existing piece in the database.",
            description = "Checks if a piece exists in the database by a given ID and updates its info if it does exist. " +
                    "Returns either the updated piece if everything worked fine or the old one if there was any problem.",
            tags = {"put","piece","update"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found"),
            @ApiResponse(responseCode = "304", description = "not updated",
                    content = {@Content(schema = @Schema(implementation = Piece.class))})
    })
    ResponseEntity<Piece> update(@RequestBody Piece piece);

    @Operation(
            summary = "Deletes an existing piece by a given ID.",
            description = "Deletes an artist from the database by a given ID. Returns nothing.",
            tags = {"delete","piece"}
    )@ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    void deleteById(@PathVariable Long id);

    @Operation(
            summary = "Deletes an existing piece by a given name.",
            description = "Deletes an artist from the database by a given name. Returns nothing.",
            tags = {"delete","piece"}
    )@ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Piece.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    void deleteByName(@PathVariable String name);
}