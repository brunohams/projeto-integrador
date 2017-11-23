/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dao.core.CargoDAO;
import factory.DAOListener;
import model.Cargo;

import java.util.Date;
import java.util.List;

/**
 * @author Aluno
 */
public class CargoSQLTeste {
    public static void main(String[] args) {

        testeDeleta(6);

    }

    public static void lista() {

        CargoDAO cargoDAO = DAOListener.getDAOFactory().getCargoDao();

        List<Cargo> list = cargoDAO.listAll();

        System.out.println(list);

    }

    public static void testeDeleta(int id) {

        CargoDAO cargoDAO = DAOListener.getDAOFactory().getCargoDao();
        Cargo cargo = new Cargo();

        cargo.setId(id);

        cargoDAO.delete(id);

    }

    public static void testeAtualiza() {

        CargoDAO cargoDAO = DAOListener.getDAOFactory().getCargoDao();
        Cargo cargo = new Cargo();

        cargo.setId(4);
        cargo.setAreaId(1);
        cargo.setNome("Teste atualizado");

        cargoDAO.update(cargo);

    }

    public static void testeInsercao() {


        CargoDAO cargoDAO = DAOListener.getDAOFactory().getCargoDao();

        Cargo cargo = new Cargo();
        cargo.setAreaId(1);
        cargo.setNome("CArgo de teste");

        cargoDAO.insert(cargo);


    }

}
