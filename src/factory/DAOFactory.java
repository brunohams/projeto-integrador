package factory;


import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import dao.core.CargoDAO;
import dao.core.ConhecimentoDAO;
import dao.core.ExperienciaDAO;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory {

    // Lista dos tipos DAO suportados pela fabrica
    public static final int MARIADB = 1;
    public static final int MYSQL   = 2;
    public static final int ORACLE  = 3;

    protected final boolean LOCAL   = false;

    public static DAOFactory getDAOFactory(int whichFactory) {

        switch (whichFactory) {
            
            case MYSQL:
                return new MySqlDAOFactory();
       
            default:
                return null;
        }
    }
    
    public void closeConn(Connection conn) {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Problemas no fechamento da conexao.");
            System.out.println(e.getMessage());
        }
    }
    
    public abstract Connection openConn();

    public abstract AreaDAO getAreaDAO();

    public abstract CandidatoDAO getCadidatoDao();
    
    public abstract CargoDAO getCargoDao();
    
    public abstract ConhecimentoDAO getConhecimentoDao();
    
    public abstract ExperienciaDAO getExperienciaDAO();
    
}
