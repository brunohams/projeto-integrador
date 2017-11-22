/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.core.AreaDAO;
import factory.DAOListener;
import model.Area;

/**
 *
 * @author Aluno
 */
public class AreaSQLTeste {
    public static void main(String[] args)
    {

        AreaDAO areaDao = DAOListener.getDAOFactory().getAreaDAO();
        Area area = new Area();

        //Realiza update
        area.setId(3);
        area.setArea("Teste alterado 2");
        areaDao.update(area);

    }

    public static void testeInsercao()
    {

        //Insere
        AreaDAO areaDao = DAOListener.getDAOFactory().getAreaDAO();
        Area area = new Area();
        area.setArea("Area teste");
        area.setId(1);
        areaDao.insert(area);


    }

}
