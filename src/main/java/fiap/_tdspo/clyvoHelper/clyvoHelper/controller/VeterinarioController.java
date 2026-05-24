package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.VeterinarioDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.VeterinarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Veterinarios")
@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {
    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioController(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Operation(
            summary = "Cria veterinário",
            description = "Insere um novo veterinario no sistema"
    )
    @PostMapping
    public Veterinario createVeterinario(@RequestBody VeterinarioDto veterinarioDto) {
        return veterinarioRepository.save(veterinarioDto.toVeterinario());
    }
}
