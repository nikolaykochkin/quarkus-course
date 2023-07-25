package name.nikolaikochkin.panache.page;

import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import name.nikolaikochkin.panache.model.Book;
import name.nikolaikochkin.panache.model.CD;

import java.util.List;

@Path("page/items")
@Produces(MediaType.TEXT_HTML)
public class ItemPage {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance book(Book book);

        public static native TemplateInstance books(List<Book> books);

        public static native TemplateInstance cd(CD cd);

        public static native TemplateInstance cds(List<CD> cds);
    }

    @GET
    @Path("books/{id}")
    public TemplateInstance showBookById(@PathParam("id") Long id) {
        return Templates.book(Book.findById(id));
    }

    @GET
    @Path("books")
    public TemplateInstance showAllBooks(@QueryParam("query") String query,
                                         @QueryParam("sort") @DefaultValue("id") String sort,
                                         @QueryParam("page") @DefaultValue("0") Integer pageIndex,
                                         @QueryParam("size") @DefaultValue("50") Integer pageSize) {
        return Templates.books(Book.find(query, Sort.by(sort)).page(pageIndex, pageSize).list());
    }

    @GET
    @Path("cds/{id}")
    public TemplateInstance showCDById(@PathParam("id") Long id) {
        return Templates.cd(CD.findById(id));
    }

    @GET
    @Path("cds")
    public TemplateInstance showAllCDs() {
        return Templates.cds(CD.listAll());
    }
}
