package ninja.kriyss;

import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Tracker tracker() throws IOException {
        Tracker tracker = new Tracker(new InetSocketAddress(6969));

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {


                return name.endsWith(".torrent");
            }
        };

        //for (File f : new File("/path/to/torrent/files").listFiles(filter)) {
          //  tracker.announce(TrackedTorrent.load(f));
        //}

        tracker.start();
        return tracker;
    }

}
