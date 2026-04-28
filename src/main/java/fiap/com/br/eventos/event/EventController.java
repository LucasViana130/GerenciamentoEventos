package fiap.com.br.eventos.event;

import fiap.com.br.eventos.participant.ParticipantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    @GetMapping
    public Page<EventProjection> findAll(Pageable pageable) {
        return eventRepository.findAllProjectedBy(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return eventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-city")
    public List<Event> findByCity(@RequestParam String city) {
        return eventRepository.findByCityIgnoreCase(city);
    }

    @GetMapping("/by-organizer/{organizerId}")
    public List<Event> findByOrganizer(@PathVariable Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }

    @GetMapping("/by-category/{categoryId}")
    public List<Event> findByCategory(@PathVariable Long categoryId) {
        return eventRepository.findByCategoryId(categoryId);
    }

    @GetMapping("/search")
    public Page<EventProjection> search(@RequestParam String name, Pageable pageable) {
        return eventRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @GetMapping("/by-date-range")
    public Page<EventProjection> findByDateRange(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to,
            Pageable pageable) {
        return eventRepository.findByEventDateBetween(from, to, pageable);
    }

    @GetMapping("/available")
    public List<Event> findAvailable() {
        return eventRepository.findAvailableEvents();
    }

    @GetMapping("/upcoming")
    public List<Event> findUpcoming() {
        return eventRepository.findByEventDateGreaterThanEqualOrderByEventDateAsc(LocalDate.now());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody @Valid Event event) {
        return eventRepository.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody @Valid Event event) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        event.setId(id);
        return ResponseEntity.ok(eventRepository.save(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/participants/{participantId}")
    public ResponseEntity<Event> addParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        var event = eventRepository.findById(eventId);
        var participant = participantRepository.findById(participantId);

        if (event.isEmpty() || participant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var e = event.get();
        var p = participant.get();

        if (e.getParticipants().contains(p)) {
            return ResponseEntity.badRequest().build();
        }

        e.getParticipants().add(p);
        return ResponseEntity.ok(eventRepository.save(e));
    }

    @DeleteMapping("/{eventId}/participants/{participantId}")
    public ResponseEntity<Event> removeParticipant(@PathVariable Long eventId, @PathVariable Long participantId) {
        var event = eventRepository.findById(eventId);
        var participant = participantRepository.findById(participantId);

        if (event.isEmpty() || participant.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var e = event.get();
        e.getParticipants().remove(participant.get());
        return ResponseEntity.ok(eventRepository.save(e));
    }

}
