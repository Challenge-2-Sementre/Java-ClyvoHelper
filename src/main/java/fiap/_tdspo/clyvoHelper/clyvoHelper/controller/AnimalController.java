package fiap._tdspo.clyvoHelper.clyvoHelper.controller;

import fiap._tdspo.clyvoHelper.clyvoHelper.dto.AnimalDto;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import fiap._tdspo.clyvoHelper.clyvoHelper.repository.AnimalRepository;

import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalRepository animalRepository;
    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostMapping
    public Animal addAnimal(@RequestBody AnimalDto animalDto) {
        System.out.println(animalDto.getNome());
        Animal animal = animalDto.toAnimal();
        return animalRepository.save(animal);
    }

    @GetMapping
    public Page findAll(Pageable pageable) {
        return animalRepository.findAll(pageable);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAnimal(@RequestParam Long id) {
        animalRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
