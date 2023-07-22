package name.nikolaikochkin.jdbc;

import java.time.Instant;

public class Artist {
    public Long id;
    public String name;
    public String bio;
    public Instant createdDate = Instant.now();

    public Artist() {
    }

    public Artist(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
//
//    public Long getId() {
//        return id;
//    }
}
