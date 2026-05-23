package fiap._tdspo.clyvoHelper.clyvoHelper.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    @ManyToOne
    private Animal animal;

    @ManyToOne
    private Veterinario veterinario;
}