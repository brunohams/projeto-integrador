/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.core.ExperienciaDAO;
import dao.core.ExperienciaDAO;
import factory.DAOListener;
import model.Experiencia;
import model.Experiencia;

import java.util.List;

/**
 * @author Aluno
 */
public class ExperienciaSQLTeste {
    public static void main(String[] args)
    {

        testeDeleta(2);

    }

    public static void lista() {

        ExperienciaDAO experienciaDAO = DAOListener.getDAOFactory().getExperienciaDAO();

        List<Experiencia> list = experienciaDAO.listAll();

        System.out.println(list);

    }

    public static void testeDeleta(int id) {

        ExperienciaDAO experienciaDAO = DAOListener.getDAOFactory().getExperienciaDAO();
        Experiencia experiencia = new Experiencia();

        experiencia.setId(id);

        experienciaDAO.delete(id);

    }

    public static void testeAtualiza() {

        ExperienciaDAO experienciaDAO = DAOListener.getDAOFactory().getExperienciaDAO();
        Experiencia experiencia = new Experiencia();

        experiencia.setId(2);
        experiencia.setNomeEmpresa("testeatalizado");
        experiencia.setCandidatoId(4);
        experiencia.setCargo("Teste atualizado");

        experienciaDAO.update(experiencia);

    }

    public static void testeInsercao() {


        ExperienciaDAO experienciaDAO = DAOListener.getDAOFactory().getExperienciaDAO();

        Experiencia experiencia = new Experiencia();
        experiencia.setCandidatoId(3);
        experiencia.setNomeEmpresa("EMRPESAde teste");
        experiencia.setCargo("CArgo de teste");

        experienciaDAO.insert(experiencia);


    }

}
