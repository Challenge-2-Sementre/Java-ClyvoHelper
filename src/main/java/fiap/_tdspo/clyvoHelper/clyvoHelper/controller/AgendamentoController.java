package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AgendamentoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import fiap._tdspo.clyvoHelper.clyvoHelper.service.AgendamentoService;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AgendamentoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Agendamentos")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;
    private AgendamentoService agendamentoService;
    public AgendamentoController(AgendamentoRepository agendamentoRepository, AgendamentoService agendamentoService) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoService = agendamentoService;
    }

    @Operation(
            summary = "Retornar veterinarios disponiveis",
            description = "Retorna uma lista com todos os veterinarios com o dia de parametro enviado como disponivel."
    )
    @GetMapping("/disponibilidade/veterinarios")
    public List<Veterinario> veterinariosDisponiveis(
            @RequestParam String data
    ) {

        return agendamentoService
                .buscarVeterinariosDisponiveis(
                        LocalDate.parse(data)
                );
    }

    @Operation(
            summary = "Busca por dias disponiveis de veterinario",
            description = "Retorna todos os dias em que um veterinario esta disponivel dentro das próximas duas semanas"
    )
    @GetMapping("/disponibilidade/horarios")
    public List<LocalDate> horariosDisponiveis(
            @RequestParam Long veterinarioId
    ) {
        return agendamentoService.buscarDatasDisponiveis(veterinarioId);
    }

    @Operation(
            summary = "Cria um agendamento",
            description = "Tomando em consideração se o veterinario está disponível em determinado dia, realiza um agendamento caso o dia requisitado não entrar em conflito com outra operação ou consulta pré-agendada com este profissional."
    )
    @PostMapping
    public Agendamento criar(
            @RequestBody AgendamentoDto agendamentoDto
    ) {
        return agendamentoService.criarAgendamento(agendamentoDto);
    }
}
