/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;


import java.util.List;
import model.Conhecimento;
import dao.core.ConhecimentoDAO;
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
public class ConhecimentoMySqlDAO implements ConhecimentoDAO{
    @Override
    public Integer insert(Conhecimento obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();
        Integer id = null;

        try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO conhecimento VALUES (NULL, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getCandidatoId());
            ps.setInt(2, obj.getAreaConhecimento());
            ps.setString(3, obj.getInstituicao());
            ps.setString(4, obj.getObservacoes());
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
    public void update(Conhecimento obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE conhecimento SET candidatoId = ?, areaConhecimento = ?, instituicao = ?, observacoes = ? WHERE (id = ?)");
            ps.setInt(1, obj.getCandidatoId());
            ps.setInt(2, obj.getAreaConhecimento());
            ps.setString(3, obj.getInstituicao());
            ps.setString(4, obj.getObservacoes());
            ps.setInt(5, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Conhecimento obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM conhecimento WHERE (id = ?)");
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Conhecimento (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Integer id) {

        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM conhecimento WHERE (id = ?)");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Conhecimento (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public List<Conhecimento> listAll() {

        List<Conhecimento> list = new ArrayList<>();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM conhecimento ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Conhecimento e = new Conhecimento();

                e.setId(rs.getInt("id"));
                e.setCandidatoId(rs.getInt("candidatoId"));
                e.setAreaConhecimento(rs.getInt("areaConhecimento"));
                e.setInstituicao(rs.getString("instituicao"));
                e.setObservacoes(rs.getString("observacoes"));

                list.add(e);
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'listAll' de Conhecimento (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);

        return list;
    }

    @Override
    public Conhecimento listByKey(Integer id) {

        Conhecimento conhecimento = new Conhecimento();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM conhecimento WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                conhecimento.setId(rs.getInt("id"));
                conhecimento.setCandidatoId(rs.getInt("candidatoId"));
                conhecimento.setAreaConhecimento(rs.getInt("areaConhecimento"));
                conhecimento.setInstituicao(rs.getString("instituicao"));
                conhecimento.setObservacoes(rs.getString("observacoes"));

            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'listById' de Conhecimento (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);

        return conhecimento;
    }
}

