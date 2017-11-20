
package util;


import java.io.Serializable;
import java.sql.*;

/**
 * Classe conexão
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
            Erro.mensagem(e);
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
            Erro.mensagem(e);
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
            Erro.mensagem(e);
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
            Erro.mensagem(e);
        }
        
        fechaConexao();
        
    }
  
}
