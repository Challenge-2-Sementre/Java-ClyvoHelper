package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AgendamentoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import fiap._tdspo.clyvoHelper.clyvoHelper.service.AgendamentoService;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AgendamentoRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;
    private AgendamentoService agendamentoService;
    public AgendamentoController(AgendamentoRepository agendamentoRepository, AgendamentoService agendamentoService) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/disponibilidade/veterinarios")
    public List<Veterinario> veterinariosDisponiveis(
            @RequestParam String data
    ) {

        return agendamentoService
                .buscarVeterinariosDisponiveis(
                        LocalDate.parse(data)
                );
    }

    @GetMapping("/disponibilidade/horarios")
    public List<LocalDate> horariosDisponiveis(
            @RequestParam Long veterinarioId
    ) {
        return agendamentoService.buscarDatasDisponiveis(veterinarioId);
    }

    @PostMapping
    public Agendamento criar(
            @RequestBody AgendamentoDto agendamentoDto
    ) {
        return agendamentoService.criarAgendamento(agendamentoDto);
    }
}
