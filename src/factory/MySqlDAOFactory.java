package factory;



import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import dao.core.CargoDAO;
import dao.core.ConhecimentoDAO;
import dao.core.ExperienciaDAO;
import dao.mysql.AreaMySqlDao;
import dao.mysql.CandidatoMySqlDAO;
import dao.mysql.CargoMySqlDAO;
import dao.mysql.ConhecimentoMySqlDAO;
import util.Erro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory {

    private static final String DRIVER  = "com.mysql.jdbc.Driver";
    private static final String URL     = "jdbc:mysql://104.131.119.105:3306/integrador";
    private static final String USER    = "integrador";
    private static final String PASS    = "presunto";

    public MySqlDAOFactory() {

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
