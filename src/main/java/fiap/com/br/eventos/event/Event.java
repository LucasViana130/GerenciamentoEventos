package fiap.com.br.eventos.event;

import fiap.com.br.eventos.category.Category;
import fiap.com.br.eventos.organizer.Organizer;
import fiap.com.br.eventos.participant.Participant;
import fiap.com.br.eventos.validation.MinCapacity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do evento é obrigatório")
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull(message = "A data do evento é obrigatória")
    @FutureOrPresent(message = "A data do evento não pode ser no passado")
    private LocalDate eventDate;

    @NotBlank(message = "A cidade é obrigatória")
    private String city;

    @Positive(message = "A capacidade deve ser um número positivo")
    @MinCapacity(min = 10)
    private Integer capacity;

    @ManyToOne
    @NotNull(message = "O organizador é obrigatório")
    private Organizer organizer;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Participant> participants = new ArrayList<>();

}
