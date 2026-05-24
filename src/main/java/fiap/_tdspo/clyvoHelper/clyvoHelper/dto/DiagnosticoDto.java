package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class DiagnosticoDto {

    @NotBlank(message = "Insira a especie do animal!")
    private String especie;

    @NotBlank(message = "Insira a idade do animal!")
    @Min(value = 0, message = "Não insira valores negativos para a idade do animal!")
    private Integer idade;

    @NotBlank(message = "Insira o peso do animal!")
    @Min(value = 0, message = "Não insira valores negativos para o peso do animal!")
    private Double peso;

    @NotBlank(message = "Insira os sintomas do animal!")
    private String sintomas;

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }
}
