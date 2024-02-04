package es.severo.gallery.controller.docs;

import es.severo.gallery.entity.Artist;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Artist API",description = "Artist-related endpoints.")
public interface ArtistApiDoc {
    @Operation(
            summary = "Retrieve all artist.",
            description = "Get all the artists from the database. Returns them in a list.",
            tags ={"get","artist","list"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Artist>> getAll();

    @Operation(
            summary = "Retrieve all artist with pieces with a price between 2 given values.",
            description = "Get all the artist that have pieces with a price between 2 given values from the database. Returns them in a list.",
            tags ={"get","artist","piece","list","price"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<List<Artist>> getAllByPrice(@RequestParam() double startPrice, @RequestParam() double endPrice);

    @Operation(
            summary = "Retrieves an artist by a given ID.",
            description = "Get an artist from the database by a given ID. Returns an artist instance.",
            tags = {"get","artist"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Artist> getById(@PathVariable("id") long id);

    @Operation(
            summary = "Retrieves an artist by a given name.",
            description = "Get an artist from the database by a given name. Returns an artist instance.",
            tags = {"get","artist"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Artist> getByName(@PathVariable("name") String name);

    @Operation(
            summary = "Adds a new artist to the database.",
            description = "Creates a new artist and adds it to the database. Returns the new artist if created correctly.",
            tags = {"post","artist","create"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    ResponseEntity<Artist> add(@RequestBody Artist artist);

    @Operation(
            summary = "Updates an existing artist in the database.",
            description = "Checks if an artist exists in the database by a given ID and updates its info if it does exist." +
                    " Returns either the updated artist if everything worked fine or the old one if there was any problem.",
            tags = {"put","artist","update"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found"),
            @ApiResponse(responseCode = "304", description = "not updated",
                    content = {@Content(schema = @Schema(implementation = Artist.class))})
    })
    ResponseEntity<Artist> update(@RequestBody Artist artist);

    @Operation(
            summary = "Deletes an existing artist by a given ID.",
            description = "Deletes an artist from the database by a given ID. Returns nothing.",
            tags = {"delete","artist"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "success",
                    content = {@Content(schema = @Schema(implementation = Artist.class))}),
            @ApiResponse(responseCode = "404",description = "not found")
    })
    void deleteById(@PathVariable Long id);

    @Operation(
            summary = "Deletes an existing artist by a given name.",
            description = "Deletes an artist from the database by a given name. Returns nothing.",
            tags = {"delete","artist"}
    )
    void deleteByName(@PathVariable String name);
}