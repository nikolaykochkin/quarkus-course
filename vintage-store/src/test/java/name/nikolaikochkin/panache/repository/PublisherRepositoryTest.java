package name.nikolaikochkin.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import name.nikolaikochkin.panache.model.Publisher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class PublisherRepositoryTest {
    @Test
    @TestTransaction
    void shouldCreateAndFindAPublisher() {
        Publisher expected = new Publisher("name");

        Publisher.persist(expected);

        assertNotNull(expected.id);

        Publisher actual = Publisher.findById(expected.id);

        assertEquals(expected.name, actual.name);
    }
}
