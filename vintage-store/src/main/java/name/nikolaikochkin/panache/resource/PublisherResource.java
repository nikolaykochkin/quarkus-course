package name.nikolaikochkin.panache.resource;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import name.nikolaikochkin.jdbc.Artist;
import name.nikolaikochkin.panache.model.Publisher;

import java.util.List;

@Path("api/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class PublisherResource {
    @GET
    @Path("{id}")
    public Publisher findPublisherById(@PathParam("id") Long id) {
        return Publisher.<Publisher>findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @GET
    public List<Publisher> listAllPublishers() {
        return Publisher.listAll();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    @POST
    public Response persistPublisher(Publisher publisher, @Context UriInfo uriInfo) {
        Publisher.persist(publisher);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(publisher.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deletePublisher(@PathParam("id") Long id) {
        Publisher.deleteById(id);
    }
}
