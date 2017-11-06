
package br.com.principal;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public final class Conexao implements Serializable
{
    private static Conexao instance;

    private static final String DRIVER_JDBC = "org.gjt.mm.mysql.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/senai";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public static Conexao getInstance() 
    {
        if (instance == null) 
        {
            instance = new Conexao();
        }
        return instance;
    }
    
    private Conexao ()
    {
        carregaDriver();
    }
    
    private void carregaDriver() 
    {
        try 
        {
            System.out.println("Carregando Driver JDBC...");
            Class.forName(DRIVER_JDBC);
            System.out.println(
            "Driver JDBC carregado com sucesso...");
        }
        catch (ClassNotFoundException e) 
        {
            System.out.println(
            "Driver JDBC nao foi carregado...");
        }
        
    }
    
    private void abreConexao()
    {
        try 
        {
            System.out.println("Conectando ao banco...");
            conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexao efetuada...");
        } catch (SQLException e) 
        {
            System.out.println("Falha na conexao...");
        }
    }
    
    private void fechaConexao() 
    {
        try 
        {
            if (statement != null) 
            {
                statement.close();
            }
            conn.close();
            System.out.println("Conexao fechada...");
        } catch (SQLException e) 
        {
            System.out.println("Problemas no fechamento da conexao...");
        }
    }
    
    public void query(String sql) 
    {
        
        abreConexao();
        
        try 
        {
            statement = (Statement) conn.createStatement();
            statement.executeUpdate(sql);
        } 
        catch (SQLException e) 
        {
            System.out.println("Problemas na insercao..."+e.getMessage());
        }
        
        fechaConexao();
        
    }
  
}
