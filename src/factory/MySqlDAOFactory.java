package factory;



import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import dao.core.CargoDAO;
import dao.core.ConhecimentoDAO;
import dao.core.ExperienciaDAO;
import dao.mysql.*;
import util.Erro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory {


    private static final String DRIVER  = "com.mysql.jdbc.Driver";
    private static String URL     = "jdbc:mysql://104.131.119.105:3306/integrador";
    private static String USER    = "integrador";
    private static String PASS    = "presunto";

    public MySqlDAOFactory() {

        if (LOCAL)
        {
            URL     = "jdbc:mysql://127.0.0.1:3306/integrador";
            USER    = "root";
            PASS    = "presunto";
        }

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Problemas ao carregar o Driver (MySQL).");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Connection openConn() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        return conn;
    }

    @Override
    public AreaDAO getAreaDAO() {
       return new AreaMySqlDao();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CandidatoDAO getCadidatoDao() {
        return new CandidatoMySqlDAO();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargoDAO getCargoDao() {
        return new CargoMySqlDAO();
    }

    @Override
    public ConhecimentoDAO getConhecimentoDao() {
        return new ConhecimentoMySqlDAO();
    }

    @Override
    public ExperienciaDAO getExperienciaDAO() {
        return new ExperienciaMySqlDAO();
    }

   

}
