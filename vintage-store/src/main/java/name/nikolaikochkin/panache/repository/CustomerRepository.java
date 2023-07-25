package name.nikolaikochkin.panache.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import name.nikolaikochkin.jpa.Customer;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public List<Customer> listAllDans() {
        return list("firstName = 'Dan'", Sort.by("lastName"));
    }
}
