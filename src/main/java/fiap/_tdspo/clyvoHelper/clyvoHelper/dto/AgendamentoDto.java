package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class AgendamentoDto {
    @NotBlank(message = "A data não pode ser vazia!")
    @FutureOrPresent(message = "Não insira valores no passado para a data!")
    private LocalDate data;

    @NotBlank(message = "Envie o id do animal!")
    private Long  animalId;

    @NotBlank(message = "Envie o id do veterinario!")
    private Long veterinarioId;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public Long getVeterinarioId() {
        return veterinarioId;
    }

    public void setVeterinarioId(Long veterinarioId) {
        this.veterinarioId = veterinarioId;
    }

    public Agendamento toAgendamento(Animal animal, Veterinario veterinario) {
        Agendamento agendamento = new Agendamento();
        agendamento.setData(data);
        agendamento.setAnimal(animal);
        agendamento.setVeterinario(veterinario);
        return agendamento;
    }
}
