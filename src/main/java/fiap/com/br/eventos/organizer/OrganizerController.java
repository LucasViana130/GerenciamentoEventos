package fiap.com.br.eventos.organizer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizers")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerRepository organizerRepository;

    @GetMapping
    public List<Organizer> findAll() {
        return organizerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizer> findById(@PathVariable Long id) {
        return organizerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-city")
    public List<Organizer> findByCity(@RequestParam String city) {
        return organizerRepository.findByCityIgnoreCase(city);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organizer create(@RequestBody @Valid Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizer> update(@PathVariable Long id, @RequestBody @Valid Organizer organizer) {
        if (!organizerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        organizer.setId(id);
        return ResponseEntity.ok(organizerRepository.save(organizer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!organizerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        organizerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
