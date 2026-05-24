package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AnimalDto {
    @NotBlank(message = "Não deixe o nome do animal em vazio!")
    private String nome;

    @NotBlank(message = "Não deixe a especie do animal em vazio!")
    private String especie;

    @NotBlank(message = "Não deixe a idade do animal em vazio!")
    @Min(value = 0, message = "Não insira valores negativos para a idade do animal!")
    private Integer idade;

    @NotBlank(message = "Não deixe o peso do animal em vazio!")
    @Min(value = 0, message = "Não insira valores negativos para o peso do animal!")
    private Double peso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public Animal toAnimal() {
        Animal animal = new Animal();
        animal.setNome(this.nome);
        animal.setEspecie(this.especie);
        animal.setIdade(this.idade);
        animal.setPeso(this.peso);
        return animal;
    }
}
