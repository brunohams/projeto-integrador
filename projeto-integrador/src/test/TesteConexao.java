/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author guilhermefvs
 */
public class TesteConexao {
    private static final String DRIVER_JDBC
        = "org.gjt.mm.mysql.Driver";
   
    private static final String URL
        = "jdbc:mysql://localhost:3306/senai";
   
    private static final String USER = "root";
    
    private static final String PASS = "";
    
    private Connection conn;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    
    public static void main(String[] args) {
        
        TesteConexao obj = new TesteConexao();
        
        obj.loadDriver();
        obj.openConn();
        obj.inserirRegistro();
        obj.inserirRegistro();
        obj.removerResitro();
        obj.alterarRegistro();
        obj.listarRegistros();  
        obj.closeConn();
    }
    
    public void loadDriver() {
        try {
            System.out.println("Carregando Driver JDBC...");
            Class.forName(DRIVER_JDBC);
            System.out.println(
            "Driver JDBC carregado com sucesso...");
            } catch (ClassNotFoundException e) {
            System.out.println(
            "Driver JDBC nao foi carregado...");
        }
    }
    
    public void openConn() {
        try {
            System.out.println("Conectando ao banco...");
            conn = DriverManager.getConnection(
            URL, USER, PASS);
            System.out.println("Conexao efetuada...");
            } catch (SQLException e) {
            System.out.println("Falha na conexao...");
        }
    }
    
    public void closeConn() {
        try {
            if (statement != null) {
            statement.close();
            }
            conn.close();
            System.out.println("Conexao fechada...");
            } catch (SQLException e) {
            System.out.println("Problemas no fechamento da conexao...");
        }
    }
    
    public void inserirRegistro() {
        try {
            statement = conn.createStatement();
            statement.executeUpdate(
            "INSERT INTO registros VALUES" + " (0, 'Prod X', 44.4)");
            System.out.println("Registro inserido com sucesso...");
            } catch (SQLException e) {
            System.out.println("Problemas na insercao...");
        }
    }
    
    public void listarRegistros() {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(
            "SELECT * FROM registros");
            while (resultSet.next()) {
            Registro reg = new Registro(
            resultSet.getInt("id"),
            resultSet.getString("nome"),
            resultSet.getDouble("peso")
            );
            System.out.println(reg);
            }
            System.out.println("Listagem realizada com sucesso...");
            } catch (SQLException e) {
            System.out.println("Problemas na listagem...");
        }
    }
    
    public void removerResitro() {
        try {
            statement = conn.createStatement();
            statement.executeUpdate
            ("DELETE FROM registros WHERE (nome = 'Prod XXX')");
            System.out.println("Registro removido com sucesso...");
            } catch (SQLException e) {
            System.out.println("Problemas na remocao...");
        }
    }
    
    public void alterarRegistro() {
        try {
            preparedStatement = conn.prepareStatement("UPDATE registros SET" + " nome = ?, peso = ? WHERE id = ?");
            preparedStatement.setString(1, "KKKKKKK");
            preparedStatement.setDouble(2, 123.90);
            preparedStatement.setInt(3, 2);
            preparedStatement.execute();
            } catch (SQLException e) {
            System.out.println("Problemas na alteracao...");
        }
    }

    public void ProdutoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
