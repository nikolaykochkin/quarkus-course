package name.nikolaikochkin.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Optional;

@Entity
@Table(name = "publisher")
public class Publisher extends PanacheEntity {
    @Column(nullable = false)
    public String name;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public Publisher() {

    }

    public Publisher(String name) {
        this.name = name;
    }

    public static Optional<Publisher> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
}
