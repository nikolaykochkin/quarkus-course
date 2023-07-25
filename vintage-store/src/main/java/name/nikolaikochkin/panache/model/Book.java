package name.nikolaikochkin.panache.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Book extends Item {
    @Column(length = 15)
    public String isbn;
    @Column(name = "nb_of_pages")
    public Integer nbOfPages;
    @Column(name = "publication_date")
    public LocalDate publicationDate;
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    public Language language;
    @ManyToOne
    public Publisher publisher;
}
