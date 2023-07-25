package name.nikolaikochkin.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import name.nikolaikochkin.jpa.Customer;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends PanacheEntity {
    @Column(name = "purchase_order_date", nullable = false)
    public LocalDate date = LocalDate.now();
    @OneToMany(mappedBy = "purchaseOrder")
    public List<OrderLine> orderLines = new ArrayList<>();
    @ManyToOne
    public Customer customer;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public void addOrderLine(OrderLine line) {
        if (orderLines == null) {
            orderLines = new ArrayList<>();
        }
        line.purchaseOrder = this;
        orderLines.add(line);
    }
}
