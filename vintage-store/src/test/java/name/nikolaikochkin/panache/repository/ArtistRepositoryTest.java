package name.nikolaikochkin.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import name.nikolaikochkin.jdbc.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {
    @Inject
    ArtistRepository repository;

    @Test
    @TestTransaction
    void shouldCreateAndFindAnArtist() {
        long count = repository.count();
        int size = repository.listAll().size();
        assertEquals(count, size);

        Artist expected = new Artist("name", "bio");

        repository.persist(expected);
        assertNotNull(expected.id);

        Artist actual = repository.findById(expected.id);

        assertEquals(expected.name, actual.name);
        assertEquals(expected.bio, actual.bio);

        assertEquals(count + 1, repository.count());
        repository.deleteById(expected.id);
        assertEquals(count, repository.count());
    }

}
