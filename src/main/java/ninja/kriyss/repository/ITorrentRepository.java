package ninja.kriyss.repository;

import ninja.kriyss.model.Torrent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITorrentRepository extends JpaRepository<Torrent, Long> {

}
