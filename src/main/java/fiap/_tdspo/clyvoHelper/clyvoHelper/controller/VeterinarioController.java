package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.VeterinarioDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.VeterinarioRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {
    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioController(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @PostMapping
    public Veterinario createVeterinario(@RequestBody VeterinarioDto veterinarioDto) {
        return veterinarioRepository.save(veterinarioDto.toVeterinario());
    }
}
