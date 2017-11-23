/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import java.util.List;
import model.Cargo;
import dao.core.CargoDAO;
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
public class CargoMySqlDAO implements CargoDAO {

    @Override
    public Integer insert(Cargo obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();
        Integer id = null;

        try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO cargo VALUES (NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getAreaId());
            ps.setString(2, obj.getNome());
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
    public void update(Cargo obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE cargo SET areaId = ?, nome = ? WHERE (id = ?)");
            ps.setInt(1, obj.getAreaId());
            ps.setString(2, obj.getNome());
            ps.setInt(3, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Cargo obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM cargo WHERE (id = ?)");
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Cargo (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Integer id) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM cargo WHERE (id = ?)");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Cargo (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public List<Cargo> listAll() {

        List<Cargo> list = new ArrayList<>();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cargo ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cargo e = new Cargo();

                e.setId(rs.getInt("id"));
                e.setAreaId(rs.getInt("areaId"));
                e.setNome(rs.getString("nome"));

                list.add(e);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'listAll' de Cargo (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);

        return list;
    }

    @Override
    public Cargo listByKey(Integer id) {

        Cargo Cargo = new Cargo();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM cargo WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cargo.setId(rs.getInt("id"));
                Cargo.setAreaId(rs.getInt("areaId"));
                Cargo.setNome(rs.getString("nome"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'listById' de Cargo (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);

        return Cargo;
    }
}
