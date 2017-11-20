package factory;



import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import dao.core.CargoDAO;
import dao.core.ConhecimentoDAO;
import dao.core.ExperienciaDAO;
import dao.mysql.AreaMySqlDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySqlDAOFactory extends DAOFactory {

    private static final String DRIVER  = "com.mysql.jdbc.Driver";
    private static final String URL     = "jdbc:mysql://localhost:3306/integrador";
    private static final String USER    = "root";
    private static final String PASS    = "";

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
           System.out.println("Problemas ao abrir conexao com o banco (MySQL).");
           System.out.println(e.getMessage());
        }

        return conn;
    }

    @Override
    public AreaDAO getAreaDAO() {
       return new AreaMySqlDao();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CandidatoDAO getCadidatoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CargoDAO getCargoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConhecimentoDAO getConhecimentoDao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ExperienciaDAO getExperienciaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

}
