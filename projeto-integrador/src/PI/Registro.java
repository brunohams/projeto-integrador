/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PI;

/**
 *
 * @author guilhermefvs
 */
public class Registro {
    private int id;
    private String nome;
    private double peso;
   
    public Registro() { }
    
    public Registro(int id, String nome, double peso) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Registro{" + "id=" + id + ", nome=" + nome + ", peso=" + peso + '}';
    }
    
    
}

