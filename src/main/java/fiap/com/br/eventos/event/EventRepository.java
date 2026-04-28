package fiap.com.br.eventos.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Page<EventProjection> findAllProjectedBy(Pageable pageable);

    List<Event> findByCityIgnoreCase(String city);

    List<Event> findByOrganizerId(Long organizerId);

    List<Event> findByCategoryId(Long categoryId);

    Page<EventProjection> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<EventProjection> findByEventDateBetween(LocalDate from, LocalDate to, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE e.capacity > SIZE(e.participants)")
    List<Event> findAvailableEvents();

    List<Event> findByEventDateGreaterThanEqualOrderByEventDateAsc(LocalDate date);

}
