package fiap.com.br.eventos.organizer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    List<Organizer> findByCityIgnoreCase(String city);

    boolean existsByEmail(String email);

}
