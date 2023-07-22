package name.nikolaikochkin.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import name.nikolaikochkin.jpa.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
