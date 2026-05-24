package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Veterinario;

public class VeterinarioDto {
    private String nome;

    private String especialidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Veterinario toVeterinario() {
        Veterinario veterinario = new Veterinario();
        veterinario.setNome(nome);
        veterinario.setEspecialidade(especialidade);
        return veterinario;
    }

}
