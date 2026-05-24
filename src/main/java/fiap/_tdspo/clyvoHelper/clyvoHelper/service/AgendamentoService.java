package fiap._tdspo.clyvoHelper.clyvoHelper.service;


import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AgendamentoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AgendamentoRepository;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AnimalRepository;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.VeterinarioRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService {

    private final AnimalRepository animalRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final VeterinarioRepository veterinarioRepository;

    public AgendamentoService(
            AnimalRepository animalRepository,
            AgendamentoRepository agendamentoRepository,
            VeterinarioRepository veterinarioRepository
    ) {
        this.animalRepository = animalRepository;
        this.agendamentoRepository = agendamentoRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Cacheable("veterinariosDisponiveis")
    public List<Veterinario> buscarVeterinariosDisponiveis(
            LocalDate data
    ) {

        List<Veterinario> todosVeterinarios =
                veterinarioRepository.findAll();

        return todosVeterinarios.stream()
                .filter(veterinario -> {

                    boolean ocupado =
                            agendamentoRepository
                                    .existsByVeterinarioIdAndData(
                                            veterinario.getId(),
                                            data
                                    );

                    return !ocupado;
                })
                .toList();
    }

    @Cacheable("horariosDisponiveis")
    public List<LocalDate> buscarDatasDisponiveis(
            Long veterinarioId
    ) {

        LocalDate hoje = LocalDate.now();

        List<LocalDate> datasDisponiveis =
                new ArrayList<>();

        for (int i = 0; i < 14; i++) {

            LocalDate data = hoje.plusDays(i);

            boolean ocupado =
                    agendamentoRepository
                            .existsByVeterinarioIdAndData(
                                    veterinarioId,
                                    data
                            );

            if (!ocupado) {
                datasDisponiveis.add(data);
            }
        }

        return datasDisponiveis;
    }

    @CacheEvict(
            value = {
                    "veterinariosDisponiveis",
                    "horariosDisponiveis"
            },
            allEntries = true
    )
    public Agendamento criarAgendamento(
            AgendamentoDto dto
    ) {

        boolean jaExiste =
                agendamentoRepository
                        .existsByVeterinarioIdAndData(
                                dto.getVeterinarioId(),
                                dto.getData()
                        );

        if (jaExiste) {

            throw new RuntimeException(
                    "Veterinario ja possui agendamento nessa data"
            );
        }

        Animal animal =
                animalRepository
                        .findById(dto.getAnimalId())
                        .orElseThrow();

        Veterinario veterinario =
                veterinarioRepository
                        .findById(dto.getVeterinarioId())
                        .orElseThrow();

        Agendamento agendamento =
                new Agendamento();

        agendamento.setData(dto.getData());
        agendamento.setAnimal(animal);
        agendamento.setVeterinario(veterinario);

        return agendamentoRepository.save(agendamento);
    }


    private Agendamento dtoToAgendamento(AgendamentoDto agendamentoDto) {
        Animal animal = animalRepository.findById(agendamentoDto.getAnimalId()).get();
        Veterinario veterinario = veterinarioRepository.findById(agendamentoDto.getVeterinarioId()).get();
        return agendamentoDto.toAgendamento(animal,veterinario);
    }
}
