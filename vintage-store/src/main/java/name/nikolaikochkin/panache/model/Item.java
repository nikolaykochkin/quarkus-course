package name.nikolaikochkin.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import name.nikolaikochkin.jdbc.Artist;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PanacheEntity {
    @Column(length = 100, nullable = false)
    public String title;
    @Column(length = 3000)
    public String description;
    @Column(nullable = false)
    public BigDecimal price;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();
    @ManyToOne
    public Artist artist;
}
