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

    @PostMapping("/movie")
    public Netflix createNetflixMovie(@RequestBody Netflix netflix) {
        return netflixRepository.save(netflix);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Netflix> updateNetflixMovie(@PathVariable String id, @RequestBody Netflix netflixDetails) {
        Netflix netflix = netflixRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Netflix not exist with id :" + id));
        netflix.setRelease_year(netflixDetails.getRelease_year());
        Netflix updatedNetflixMovie = netflixRepository.save(netflix);
        return ResponseEntity.ok(updatedNetflixMovie);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteNetflixMovie(@PathVariable String id) {
        Netflix netflix = netflixRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + id));
        netflixRepository.delete(netflix);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
