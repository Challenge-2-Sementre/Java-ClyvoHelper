package fiap._tdspo.clyvoHelper.clyvoHelper.service;


import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AgendamentoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.HorarioAtendimento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AgendamentoRepository;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AnimalRepository;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.HorarioAtendimentoRepository;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.VeterinarioRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableCaching
public class AgendamentoService {

    private final AnimalRepository animalRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final HorarioAtendimentoRepository horarioAtendimentoRepository;

    public AgendamentoService(
            AnimalRepository animalRepository,
            AgendamentoRepository agendamentoRepository,
            VeterinarioRepository veterinarioRepository,
            HorarioAtendimentoRepository horarioAtendimentoRepository
    ) {
        this.animalRepository = animalRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.horarioAtendimentoRepository = horarioAtendimentoRepository;
    }

    @Cacheable("veterinariosDisponiveis")
    public List<Veterinario> buscarVeterinariosDisponiveis(
            LocalDate data
    ) {

        System.out.println("CONSULTANDO VETERINARIOS DISPONIVEIS");

        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23, 59, 59);

        List<Veterinario> todosVeterinarios =
                veterinarioRepository.findAll();

        return todosVeterinarios.stream()
                .filter(veterinario -> {

                    List<Agendamento> agendamentos =
                            agendamentoRepository
                                    .findByVeterinarioIdAndDataHoraBetween(
                                            veterinario.getId(),
                                            inicio,
                                            fim
                                    );

                    return agendamentos.size() < 4;
                })
                .toList();
    }

    @Cacheable("horariosDisponiveis")
    public List<LocalDate> buscarHorariosDisponiveis(
            Long veterinarioId
    ) {
        LocalDate hoje = LocalDate.now();

        List<LocalDate> datasDisponiveis = new ArrayList<>();

        for (int i = 0; i < 14; i++) {

            LocalDate dataAtual = hoje.plusDays(i);

            DayOfWeek diaSemana = dataAtual.getDayOfWeek();

            List<HorarioAtendimento> horariosTrabalho =
                    horarioAtendimentoRepository
                            .findByVeterinarioIdAndDiaDaSemana(
                                    veterinarioId,
                                    diaSemana
                            );

            if (horariosTrabalho.isEmpty()) {
                continue;
            }

            LocalDateTime inicio = dataAtual.atStartOfDay();
            LocalDateTime fim = dataAtual.atTime(23,59,59);

            List<Agendamento> agendamentos =
                    agendamentoRepository
                            .findByVeterinarioIdAndDataHoraBetween(
                                    veterinarioId,
                                    inicio,
                                    fim
                            );

            int totalHorarios =
                    horariosTrabalho.size();

            int totalAgendamentos =
                    agendamentos.size();

            if (totalAgendamentos < totalHorarios) {
                datasDisponiveis.add(dataAtual);
            }
        }

        return datasDisponiveis;
    }

    public Agendamento criarAgendamento(AgendamentoDto agendamentoDto) {
        Agendamento agendamento = dtoToAgendamento(agendamentoDto);
        return agendamentoRepository.save(agendamento);
    }

    private Agendamento dtoToAgendamento(AgendamentoDto agendamentoDto) {
        Animal animal = animalRepository.findById(agendamentoDto.getAnimalId()).get();
        Veterinario veterinario = veterinarioRepository.findById(agendamentoDto.getVeterinarioId()).get();
        return agendamentoDto.toAgendamento(animal,veterinario);
    }
}
