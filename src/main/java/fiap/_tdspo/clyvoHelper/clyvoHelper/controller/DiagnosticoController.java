package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.DiagnosticoDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.dto.DiagnosticoResponseDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.service.DiagnosticoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Diagnosticos")
@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoController(
            DiagnosticoService diagnosticoService
    ) {
        this.diagnosticoService = diagnosticoService;
    }

    @Operation(
            summary = "Realiza um diagnóstico auxiliar",
            description = "Faz uma requisição à inteligência artificial, analisando os dados enviados para a criação de um pré-diagnóstico que visa auxiliar o veterinário"
    )
    @PostMapping
    public DiagnosticoResponseDto gerarDiagnostico(
            @RequestBody DiagnosticoDto dto
    ) {

        return diagnosticoService
                .gerarDiagnostico(dto);
    }
}