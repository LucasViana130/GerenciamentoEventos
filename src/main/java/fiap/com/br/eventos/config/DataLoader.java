package fiap.com.br.eventos.config;

import fiap.com.br.eventos.category.Category;
import fiap.com.br.eventos.category.CategoryRepository;
import fiap.com.br.eventos.event.Event;
import fiap.com.br.eventos.event.EventRepository;
import fiap.com.br.eventos.organizer.Organizer;
import fiap.com.br.eventos.organizer.OrganizerRepository;
import fiap.com.br.eventos.participant.Participant;
import fiap.com.br.eventos.participant.ParticipantRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final OrganizerRepository organizerRepository;
    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    @Override
    public void run(@Nonnull String... args) {

        var tech = Category.builder().name("Tecnologia").description("Eventos de tecnologia e inovação").build();
        var music = Category.builder().name("Música").description("Shows e festivais musicais").build();
        var business = Category.builder().name("Negócios").description("Conferências e feiras de negócios").build();
        var sports = Category.builder().name("Esportes").description("Competições e eventos esportivos").build();
        var culture = Category.builder().name("Cultura").description("Eventos culturais e artísticos").build();

        categoryRepository.saveAll(List.of(tech, music, business, sports, culture));

        var organizers = List.of(
                Organizer.builder().name("TechBrasil Eventos").email("contato@techbrasil.com").phone("11999990001").city("São Paulo").build(),
                Organizer.builder().name("Rio Cultura").email("info@riocult.com").phone("21999990002").city("Rio de Janeiro").build(),
                Organizer.builder().name("Sul Shows").email("shows@sulshows.com").phone("51999990003").city("Porto Alegre").build(),
                Organizer.builder().name("Nordeste Fest").email("contato@nordestefest.com").phone("81999990004").city("Recife").build(),
                Organizer.builder().name("Capital Events").email("capital@capitalevents.com").phone("61999990005").city("Brasília").build()
        );

        organizerRepository.saveAll(organizers);

        var participants = List.of(
                Participant.builder().name("Ana Souza").email("ana@email.com").phone("11911110001").build(),
                Participant.builder().name("Bruno Lima").email("bruno@email.com").phone("11911110002").build(),
                Participant.builder().name("Carla Mendes").email("carla@email.com").phone("21911110003").build(),
                Participant.builder().name("Diego Rocha").email("diego@email.com").phone("51911110004").build(),
                Participant.builder().name("Fernanda Costa").email("fernanda@email.com").phone("81911110005").build(),
                Participant.builder().name("Gabriel Santos").email("gabriel@email.com").phone("61911110006").build(),
                Participant.builder().name("Helena Alves").email("helena@email.com").phone("11911110007").build(),
                Participant.builder().name("Igor Martins").email("igor@email.com").phone("21911110008").build()
        );

        participantRepository.saveAll(participants);

        var events = List.of(
                Event.builder()
                        .name("FIAP Next 2026")
                        .description("Maior evento de inovação e tecnologia do Brasil")
                        .eventDate(LocalDate.of(2026, 10, 15))
                        .city("São Paulo")
                        .capacity(500)
                        .organizer(organizers.get(0))
                        .category(tech)
                        .participants(new ArrayList<>(List.of(participants.get(0), participants.get(1), participants.get(6))))
                        .build(),
                Event.builder()
                        .name("Rock in Rio 2026")
                        .description("O maior festival de música do mundo")
                        .eventDate(LocalDate.of(2026, 9, 20))
                        .city("Rio de Janeiro")
                        .capacity(100000)
                        .organizer(organizers.get(1))
                        .category(music)
                        .participants(new ArrayList<>(List.of(participants.get(2), participants.get(3))))
                        .build(),
                Event.builder()
                        .name("Campus Party Brasil 2026")
                        .description("Festival de ciência, tecnologia, cultura e empreendedorismo")
                        .eventDate(LocalDate.of(2026, 11, 5))
                        .city("São Paulo")
                        .capacity(1000)
                        .organizer(organizers.get(0))
                        .category(tech)
                        .participants(new ArrayList<>(List.of(participants.get(0), participants.get(4), participants.get(5))))
                        .build(),
                Event.builder()
                        .name("Fórum de Empreendedorismo Sul")
                        .description("Encontro de empreendedores da região sul")
                        .eventDate(LocalDate.of(2026, 8, 22))
                        .city("Porto Alegre")
                        .capacity(300)
                        .organizer(organizers.get(2))
                        .category(business)
                        .participants(new ArrayList<>(List.of(participants.get(3), participants.get(6))))
                        .build(),
                Event.builder()
                        .name("Maratona de São Paulo")
                        .description("Corrida de rua com percurso pela cidade")
                        .eventDate(LocalDate.of(2026, 12, 1))
                        .city("São Paulo")
                        .capacity(5000)
                        .organizer(organizers.get(4))
                        .category(sports)
                        .participants(new ArrayList<>(List.of(participants.get(1), participants.get(7))))
                        .build(),
                Event.builder()
                        .name("Mostra de Cinema Recife")
                        .description("Exibição de filmes nacionais e internacionais")
                        .eventDate(LocalDate.of(2026, 7, 10))
                        .city("Recife")
                        .capacity(200)
                        .organizer(organizers.get(3))
                        .category(culture)
                        .participants(new ArrayList<>(List.of(participants.get(4), participants.get(5))))
                        .build()
        );

        eventRepository.saveAll(events);
    }

}