package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AnimalDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AnimalRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Animais")
@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Operation(
            summary = "Cadastrar animal",
            description = "Cadastra um novo animal no sistema veterinario"
    )
    @PostMapping
    public Animal addAnimal(@RequestBody AnimalDto animalDto) {
        System.out.println(animalDto.getNome());
        Animal animal = animalDto.toAnimal();
        return animalRepository.save(animal);
    }

    @Operation(
            summary = "Listar animais",
            description = "Retorna uma lista paginada de animais cadastrados"
    )
    @GetMapping
    public Page findAll(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    @Operation(
            summary = "Deletar animal",
            description = "Remove um animal do sistema a partir do ID informado"
    )
    @DeleteMapping
    public ResponseEntity<Void> deleteAnimal(@RequestParam Long id) {
        animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
