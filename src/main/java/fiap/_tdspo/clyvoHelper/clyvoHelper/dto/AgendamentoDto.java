package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Agendamento;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class AgendamentoDto {
    private LocalDateTime dataHora;

    private Long  animalId;

    private Long veterinarioId;

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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
        agendamento.setDataHora(dataHora);
        agendamento.setAnimal(animal);
        agendamento.setVeterinario(veterinario);
        return agendamento;
    }
}
