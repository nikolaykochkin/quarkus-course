package name.nikolaikochkin.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import name.nikolaikochkin.jdbc.Artist;
import name.nikolaikochkin.jpa.Customer;
import name.nikolaikochkin.panache.model.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PurchaseOrderRepositoryTest {
    @Inject
    CustomerRepository customerRepository;

    @Test
    @TestTransaction
    void shouldCreateAndFindAPurchaseOrder() {
        Artist artist = new Artist("artist name", "artist bio");

        Publisher publisher = new Publisher("publisher name");

        Book book = new Book();
        book.title = "title of the book";
        book.nbOfPages = 500;
        book.price = new BigDecimal(10);
        book.language = Language.ENGLISH;
        book.publisher = publisher;
        book.artist = artist;

        Book.persist(book);

        Customer customer = new Customer("customer first name", "customer last name", "customer email");
        customerRepository.persist(customer);

        PurchaseOrder expected = new PurchaseOrder();
        expected.customer = customer;
        expected.date = LocalDate.now();

        OrderLine line = new OrderLine();
        line.item = book;
        line.quantity = 2;

        expected.addOrderLine(line);

        PurchaseOrder.persist(expected);

        assertNotNull(expected.id);

        PurchaseOrder actual = PurchaseOrder.findById(expected.id);

        assertNotNull(actual);
        assertEquals(expected.date, actual.date);
        assertEquals(expected.orderLines.size(), actual.orderLines.size());
    }
}
