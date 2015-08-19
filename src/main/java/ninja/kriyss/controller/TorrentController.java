package ninja.kriyss.controller;

import com.turn.ttorrent.tracker.Tracker;
import ninja.kriyss.model.Torrent;
import ninja.kriyss.repository.ITorrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/torrents")
public class TorrentController {

    @Autowired private ITorrentRepository repo;
    @Autowired private Tracker tracker;

    @RequestMapping(method = RequestMethod.GET)
    public List<Torrent> getAll() {
        return repo.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Torrent getOne(@PathVariable Long id) {
        return repo.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public void post(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            repo.save(new Torrent(file.getOriginalFilename()));
        }
    }

    @RequestMapping(value = "/download/file", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) throws Exception{
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-disposition", "attachment; filename=Chrysanthemum.jpg.torrent");
        IOUtils.copy(new FileInputStream("C:/torrents/Chrysanthemum.jpg.torrent"), response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public ResponseEntity<Torrent> getByName(@PathVariable String name) {
        return repo.findByName(name)
                .map(torrent -> new ResponseEntity<>(torrent, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
