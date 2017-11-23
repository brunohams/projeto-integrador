/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import java.util.List;
import model.Area;
import dao.core.AreaDAO;
import factory.DAOListener;
import util.Erro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class AreaMySqlDao implements AreaDAO{

      @Override
    public Integer insert(Area obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();
        Integer id = null;

        try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO area VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getArea());
            ps.executeUpdate();

            // para retornar o id da pessoa adicionada
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            rs.close();
            ps.close();

        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        obj.setId(id);
        DAOListener.getDAOFactory().closeConn(conn);

        return id;
    }

    @Override
    public void update(Area obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE area SET nome = ? WHERE (id = ?)");
            ps.setString(1, obj.getArea());
            ps.setInt(2, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Area obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM area WHERE (id = ?)");
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Area (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Integer id) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM area WHERE (id = ?)");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Area (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public List<Area> listAll() {
        
        List<Area> list = new ArrayList<>();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM area ORDER BY id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Area e = new Area();
                
                e.setId(rs.getInt("id"));
               
                               
                list.add(e);
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Problemas no 'listAll' de Area (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
        
        return list;
    }

    @Override
    public Area listByKey(Integer id) {
        
        Area Area = new Area();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM area WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Area.setId(rs.getInt("id"));
            
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Problemas no 'listById' de Area (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
        
        return Area;
    }
}
