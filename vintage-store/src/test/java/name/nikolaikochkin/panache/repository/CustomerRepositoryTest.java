package name.nikolaikochkin.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import name.nikolaikochkin.jpa.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {
    @Inject
    CustomerRepository repository;

    @Test
    @TestTransaction
    void shouldCreateAndFindCustomer() {
        Customer expected = new Customer("name", "last name", "email@email.com");

        repository.persist(expected);
        assertNotNull(expected.id);

        Customer actual = repository.findById(expected.id);

        assertEquals(expected.firstName, actual.firstName);
        assertEquals(expected.lastName, actual.lastName);
        assertEquals(expected.email, actual.email);
    }
}
