package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.cache.AgendamentoService;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AgendamentoRepository;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AnimalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository agendamentoRepository;
    private AgendamentoService agendamentoService;
    public AgendamentoController(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @GetMapping("/disponibilidade")
    public List<LocalTime> disponibilidade(
            @RequestParam Long veterinarioId,
            @RequestParam String data
    ) {

        return agendamentoService.verificarDisponibilidade(
                veterinarioId,
                LocalDate.parse(data)
        );
    }
}
