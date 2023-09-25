package edu.ucmo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class NetflixController {
    @Autowired
    private NetflixRepository netflixRepository;

    @GetMapping("/movies")
    public List<Netflix> getAllNetflixMovies() {
        return netflixRepository.findAll();
    }

    @GetMapping("/movie/{title}")
    public Netflix getNetflixMovie(@PathVariable String title) throws Exception{
        return netflixRepository.findByTitle(title);
    }


    @PostMapping("/movie")
    public Netflix createNetflixMovie(@RequestBody Netflix netflix) {
        return netflixRepository.save(netflix);
    }

    @PutMapping("/movies/{title}")
    public ResponseEntity<Netflix> updateNetflixMovie(@PathVariable String title, @RequestBody Netflix netflixDetails) throws Exception {
        Netflix netflix = netflixRepository.findByTitle(title);
        netflix.setId(netflixDetails.getId());
        netflix.setTitle(netflixDetails.getTitle());
        netflix.setDescription(netflixDetails.getDescription());
        netflix.setRuntime(netflixDetails.getRuntime());
        netflix.setImdb_score(netflixDetails.getImdb_score());
        Netflix updatedNetflixMovie = netflixRepository.save(netflix);
        return ResponseEntity.ok(updatedNetflixMovie);
    }

    @DeleteMapping("/movies/{title}")
    public ResponseEntity<Map<String, Boolean>> deleteNetflixMovie(@PathVariable String title) throws Exception{
        Netflix netflix = netflixRepository.findByTitle(title);
        netflixRepository.delete(netflix);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
