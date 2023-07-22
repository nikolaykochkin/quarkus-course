package name.nikolaikochkin.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ArtistRepositoryTest {
    @Inject
    ArtistRepository repository;

    @Test
    void shouldCreateAndFindAnArtist() throws SQLException {
        Artist expected = new Artist("name", "bio");

        repository.persist(expected);
        assertNotNull(expected.id);

        Artist actual = repository.findById(expected.id);

        assertEquals(expected.name, actual.name);
        assertEquals(expected.bio, actual.bio);
    }
}
