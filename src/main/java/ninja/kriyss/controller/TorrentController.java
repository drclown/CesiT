package ninja.kriyss.controller;

import ninja.kriyss.model.Torrent;
import ninja.kriyss.repository.ITorrentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/torrents")
public class TorrentController {

    @Autowired private ITorrentRepository repo;

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
}