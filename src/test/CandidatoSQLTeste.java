/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Candidato.EstadoCivil;
import model.Candidato.Sexo;

import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import factory.DAOListener;
import model.Area;
import model.Candidato;
import util.Array;
import util.Data;

/**
 *
 * @author Aluno
 */
public class CandidatoSQLTeste {
    public static void main(String[] args)
    {

        lista();

    }

    public static void lista()
    {

        CandidatoDAO candidatoDAO = DAOListener.getDAOFactory().getCadidatoDao();

        List<Candidato> list = candidatoDAO.listAll();

        System.out.println(list);

    }

    public static void testeDeleta(int id)
    {

        CandidatoDAO candidatoDAO = DAOListener.getDAOFactory().getCadidatoDao();
        Candidato candidato = new Candidato();

        candidato.setId(id);

        candidatoDAO.delete(1);

    }

    public static void testeAtualiza()
    {

        CandidatoDAO candidatoDAO = DAOListener.getDAOFactory().getCadidatoDao();
        Candidato candidato = new Candidato();

        candidato.setId(1);

        candidato.setCodigo("testeCodigo");
        candidato.setNome("Teste Hammes");
        candidato.setCpf("09327400909");
        candidato.setSexo(Sexo.Feminino);
        candidato.setEmail("bruno.hams@hotmail.com");
        candidato.setTelefone("84650927");
        candidato.setDataNascimento(new Date());
        candidato.setRg("89191321");
        candidato.setEstadoCivil(EstadoCivil.Casado);
        candidato.setNacionalidade("Brasileiro");
        candidato.setCep("89055190");
        candidato.setEndereco("Willibald lemcke");
        candidato.setNumero("213");
        candidato.setBairro("Fortaleza");
        candidato.setComplemento("Casa");
        candidato.setCidade("Blumenau");
        candidato.setUf("SC");
        candidato.setPretensaoSalarial(31231.0D);



        candidatoDAO.update(candidato);

    }

    public static void testeInsercao()
    {


        CandidatoDAO candidatoDAO = DAOListener.getDAOFactory().getCadidatoDao();
        Candidato candidato = new Candidato();
        candidato.setCodigo("testeCodigo");
        candidato.setNome("Bruno Rafael Hammes");
        candidato.setCpf("09327400909");
        candidato.setSexo(Sexo.Feminino);
        candidato.setEmail("bruno.hams@hotmail.com");
        candidato.setTelefone("84650927");
        candidato.setDataNascimento(new Date());
        candidato.setRg("89191321");
        candidato.setEstadoCivil(EstadoCivil.Casado);
        candidato.setNacionalidade("Brasileiro");
        candidato.setCep("89055190");
        candidato.setEndereco("Willibald lemcke");
        candidato.setNumero("213");
        candidato.setBairro("Fortaleza");
        candidato.setComplemento("Casa");
        candidato.setCidade("Blumenau");
        candidato.setUf("SC");
        candidato.setPretensaoSalarial(31231.0D);

        candidatoDAO.insert(candidato);


    }

}
