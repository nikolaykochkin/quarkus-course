package name.nikolaikochkin.jpa;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    public Long id;
    @Column(name = "first_name", nullable = false, length = 50)
    public String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    public String lastName;
    @Column(name = "e_mail", nullable = false)
    public String email;
    @Column(name = "created_date", nullable = false)
    public Instant createdDate = Instant.now();

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
