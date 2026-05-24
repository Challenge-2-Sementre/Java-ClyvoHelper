package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.DiagnosticoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.dto.DiagnosticoResponseDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.service.DiagnosticoService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoController(
            DiagnosticoService diagnosticoService
    ) {
        this.diagnosticoService = diagnosticoService;
    }

    @PostMapping
    public DiagnosticoResponseDto gerarDiagnostico(
            @RequestBody DiagnosticoDto dto
    ) {

        return diagnosticoService
                .gerarDiagnostico(dto);
    }
}