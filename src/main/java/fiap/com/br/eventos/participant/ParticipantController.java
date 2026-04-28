package fiap.com.br.eventos.participant;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantRepository participantRepository;

    @GetMapping
    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> findById(@PathVariable Long id) {
        return participantRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Participant> search(@RequestParam String name) {
        return participantRepository.findByNameContainingIgnoreCase(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Participant create(@RequestBody @Valid Participant participant) {
        return participantRepository.save(participant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> update(@PathVariable Long id, @RequestBody @Valid Participant participant) {
        if (!participantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        participant.setId(id);
        return ResponseEntity.ok(participantRepository.save(participant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!participantRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        participantRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
