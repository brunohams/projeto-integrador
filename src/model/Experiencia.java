/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Aluno
 */
public class Experiencia {
    private int id;
    private String nomeEmpresa;
    private Date dataInicio;
    private Date dataFim;
    private String cargo;
    private int candidatoId;

    public Experiencia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(int candidatoId) {
        this.candidatoId = candidatoId;
    }

    @Override
    public String toString() {
        return "Experiencia{" +
                "id=" + id +
                ", nomeEmpresa='" + nomeEmpresa + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", cargo='" + cargo + '\'' +
                ", candidatoId=" + candidatoId +
                '}';
    }
}
