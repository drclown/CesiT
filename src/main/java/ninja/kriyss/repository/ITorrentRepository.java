package ninja.kriyss.repository;

import ninja.kriyss.model.Torrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITorrentRepository extends JpaRepository<Torrent, Long> {
    Optional<Torrent> findByName(String name);

}
