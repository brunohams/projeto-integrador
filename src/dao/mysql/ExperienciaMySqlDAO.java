/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;

import dao.core.ExperienciaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import factory.DAOListener;
import model.Cargo;
import model.Experiencia;
import util.Erro;

/**
 *
 * @author Aluno
 */
public class ExperienciaMySqlDAO implements ExperienciaDAO {

    @Override
    public Integer insert(Experiencia obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();
        Integer id = null;

        try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO experiencia VALUES (NULL, ?, NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getNomeEmpresa());
            ps.setString(2, obj.getCargo());
            ps.setInt(3, obj.getCandidatoId());
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
    public void update(Experiencia obj) {
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE experiencia SET nomeEmpresa = ?, cargo = ?, candidatoId = ? WHERE (id = ?)");
            ps.setString(1, obj.getNomeEmpresa());
            ps.setString(2, obj.getCargo());
            ps.setInt(3, obj.getCandidatoId());
            ps.setInt(4, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Experiencia obj) {
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM experiencia WHERE (id = ?)");
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Experiencia (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);    }

    @Override
    public void delete(Integer id) {
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM experiencia WHERE (id = ?)");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Experiencia (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);    }

    @Override
    public List<Experiencia> listAll() {
        List<Experiencia> list = new ArrayList<>();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM experiencia ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Experiencia e = new Experiencia();

                e.setId(rs.getInt("id"));
                e.setNomeEmpresa(rs.getString("nomeEmpresa"));
                e.setCargo(rs.getString("cargo"));
                e.setCandidatoId(rs.getInt("candidatoId"));

                list.add(e);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'listAll' de Experiencia (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);

        return list;    }

    @Override
    public Experiencia listByKey(Integer id) {
        Experiencia experiencia = new Experiencia();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM experiencia WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                experiencia.setId(rs.getInt("id"));
                experiencia.setNomeEmpresa(rs.getString("nomeEmpresa"));
                experiencia.setCargo(rs.getString("cargo"));
                experiencia.setCandidatoId(rs.getInt("candidatoId"));
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'listById' de Experiencia (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);

        return experiencia;
    }
    
}
