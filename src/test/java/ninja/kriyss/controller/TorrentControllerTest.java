package ninja.kriyss.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ninja.kriyss.App;
import ninja.kriyss.model.Torrent;
import ninja.kriyss.repository.ITorrentRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kriyss on 18/08/2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = App.class, initializers = ConfigFileApplicationContextInitializer.class)
public class TorrentControllerTest {

    @Autowired private ITorrentRepository    repo;
    @Autowired private WebApplicationContext wac;

    private ObjectMapper mapper;
    private MockMvc      mockMvc;

    @Before
    public void initTest() {
        // initialisation du contexte de l'application
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        mapper = new ObjectMapper();
        //clear de la base
        repo.deleteAll();
        // creation des entitées pour les tests
        repo.save(Arrays.asList(
                new Torrent("Torrent1"),
                new Torrent("Torrent2"),
                new Torrent("Torrent3"),
                new Torrent("Torrent4"),
                new Torrent("Torrent5")));
    }

    @Test
    public void should_get_all_torrent() throws Exception {
           mockMvc
                   .perform(get("/torrents"))
                   .andExpect(jsonPath("$").isArray())
                   .andExpect(jsonPath("$", Matchers.hasSize(5)))
                   .andExpect(jsonPath("$..name", hasItems("Torrent1", "Torrent2", "Torrent3", "Torrent4", "Torrent5")));
    }

    @Test
    public void should_get_one_torrent() throws Exception {
        mockMvc
                .perform(get("/torrents/1"))
                .andExpect(jsonPath("$.name", is("Torrent1")));
    }

    @Test
    public void should_post_one_torrent() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("file", "filename.txt", "text/plain", "some xml".getBytes());

        mockMvc
                .perform(fileUpload("/torrents")
                        .file(firstFile))
                    .andExpect(status().isOk());

        mockMvc
                .perform(get("/torrents"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(6)))
                .andExpect(jsonPath("$..name", hasItems("Torrent1", "Torrent2", "Torrent3", "Torrent4", "Torrent5", "filename.txt")));
    }
}
