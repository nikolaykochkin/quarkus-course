package name.nikolaikochkin.jdbc;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Timestamp;

@ApplicationScoped
public class ArtistRepository {
    @Inject
    DataSource dataSource;

    public void persist(Artist artist) throws SQLException {
        String sql = "INSERT INTO t_artist (id, name, bio, created_date) VALUES (?, ?, ?, ?)";
        artist.id = 0L;
        try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setLong(1, artist.id);
            ps.setString(2, artist.name);
            ps.setString(3, artist.bio);
            ps.setTimestamp(4, Timestamp.from(artist.createdDate));
            ps.executeUpdate();
        }
    }

    public Artist findById(Long id) throws SQLException {
        String sql = "SELECT id, name, bio, created_date FROM t_artist WHERE id = ?";
        Artist artist = new Artist();
        try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            var rs = ps.executeQuery();
            if (rs.next()) {
                artist.id = rs.getLong(1);
                artist.name = rs.getString(2);
                artist.bio = rs.getString(3);
                artist.createdDate = rs.getTimestamp(4).toInstant();
                return artist;
            }
        }
        return null;
    }
}
