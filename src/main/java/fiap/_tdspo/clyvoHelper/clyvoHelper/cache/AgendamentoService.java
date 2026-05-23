package fiap._tdspo.clyvoHelper.clyvoHelper.cache;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@EnableCaching
public class AgendamentoService {

    @Cacheable("horarios")
    public List<LocalTime> verificarDisponibilidade(
            Long veterinarioId,
            LocalDate data
    ) {

        System.out.println("CONSULTANDO BANCO...");

        return List.of(
                LocalTime.of(9,0),
                LocalTime.of(10,0),
                LocalTime.of(14,0)
        );
    }
}
