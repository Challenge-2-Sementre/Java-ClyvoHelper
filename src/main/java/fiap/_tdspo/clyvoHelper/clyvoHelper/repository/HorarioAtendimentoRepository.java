package fiap._tdspo.clyvoHelper.clyvoHelper.repository;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.HorarioAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;
import java.util.List;

public interface HorarioAtendimentoRepository
        extends JpaRepository<HorarioAtendimento, Long> {

    List<HorarioAtendimento>
    findByVeterinarioIdAndDiaDaSemana(
            Long veterinarioId,
            DayOfWeek diaSemana
    );
}
