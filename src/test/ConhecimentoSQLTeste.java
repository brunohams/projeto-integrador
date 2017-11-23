/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.core.ConhecimentoDAO;
import factory.DAOListener;
import model.Conhecimento;

import java.util.List;

/**
 * @author Aluno
 */
public class ConhecimentoSQLTeste {
    public static void main(String[] args) {

        lista();

    }

    public static void lista() {

        ConhecimentoDAO conhecimentoDAO = DAOListener.getDAOFactory().getConhecimentoDao();

        List<Conhecimento> list = conhecimentoDAO.listAll();

        System.out.println(list);

    }

    public static void testeDeleta(int id) {

        ConhecimentoDAO conhecimentoDAO = DAOListener.getDAOFactory().getConhecimentoDao();
        Conhecimento conhecimento = new Conhecimento();

        conhecimento.setId(id);

        conhecimentoDAO.delete(id);

    }

    public static void testeAtualiza() {

        ConhecimentoDAO conhecimentoDAO = DAOListener.getDAOFactory().getConhecimentoDao();
        Conhecimento conhecimento = new Conhecimento();

        conhecimento.setId(2);

        conhecimento.setCandidatoId(4);
        conhecimento.setAreaConhecimento(1);
        conhecimento.setObservacoes("teste 234");
        conhecimento.setInstituicao("olaMariline");

        conhecimentoDAO.update(conhecimento);

    }

    public static void testeInsercao() {


        ConhecimentoDAO conhecimentoDAO = DAOListener.getDAOFactory().getConhecimentoDao();

        Conhecimento conhecimento = new Conhecimento();
        conhecimento.setAreaConhecimento(1);
        conhecimento.setCandidatoId(4);
        conhecimento.setInstituicao("teste de instutuicao");
        conhecimento.setObservacoes("bem legal lá");

        conhecimentoDAO.insert(conhecimento);


    }

}
