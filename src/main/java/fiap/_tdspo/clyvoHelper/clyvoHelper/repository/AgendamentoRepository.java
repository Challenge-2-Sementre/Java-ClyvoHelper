package fiap._tdspo.clyvoHelper.clyvoHelper.repository;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

   // List<Agendamento> findByVeterinarioIdAndData(Long veterinarioId, LocalDate data);

    List<Agendamento> findByVeterinarioIdAndDataHoraBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fim);
}
