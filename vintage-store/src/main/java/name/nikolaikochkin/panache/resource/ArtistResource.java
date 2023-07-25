package name.nikolaikochkin.panache.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import name.nikolaikochkin.jdbc.Artist;
import name.nikolaikochkin.panache.repository.ArtistRepository;

import java.util.List;

@Path("api/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ArtistResource {
    @Inject
    ArtistRepository repository;

    @GET
    @Path("{id}")
    public Artist findArtistById(@PathParam("id") Long id) {
        return repository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @GET
    public List<Artist> listAllArtists() {
        return repository.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistArtist(Artist artist, @Context UriInfo uriInfo) {
        repository.persist(artist);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(artist.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteArtist(@PathParam("id") Long id) {
        repository.deleteById(id);
    }
}
