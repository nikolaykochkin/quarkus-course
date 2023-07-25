package name.nikolaikochkin.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "purchase_order_line")
public class OrderLine extends PanacheEntity {
    @ManyToOne
    public Item item;

    @Column(nullable = false)
    public Integer quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id")
    public PurchaseOrder purchaseOrder;

    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();
}
