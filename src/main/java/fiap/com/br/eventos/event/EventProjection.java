package fiap.com.br.eventos.event;

import java.time.LocalDate;

public interface EventProjection {
    Long getId();
    String getName();
    String getCity();
    LocalDate getEventDate();
    Integer getCapacity();
}
