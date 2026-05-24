package fiap._tdspo.clyvoHelper.clyvoHelper.dto;

import fiap._tdspo.clyvoHelper.clyvoHelper.entity.Animal;

public class AnimalDto {
    private String nome;
    private String especie;
    private Integer idade;
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
