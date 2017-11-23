/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.mysql;
import java.util.Date;
import model.Candidato.EstadoCivil;
import model.Candidato.Sexo;

import dao.core.CandidatoDAO;
import factory.DAOListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Candidato;
import util.Data;
import util.Erro;

/**
 *
 * @author Aluno
 */
public class CandidatoMySqlDAO implements CandidatoDAO{

  @Override
    public Integer insert(Candidato obj) {

        Connection conn = DAOListener.getDAOFactory().openConn();
        Integer id = null;

        try {

            PreparedStatement ps = conn.prepareStatement("INSERT INTO candidato VALUES (NULL,?,?,?,?,?,?,?,?,?,?,NULL,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getSexo().toString());
            ps.setString(5, obj.getEmail());
            ps.setString(6, obj.getTelefone());
            ps.setString(7, Data.dateBanco(obj.getDataNascimento()));
            ps.setString(8, obj.getRg());
            ps.setString(9, obj.getEstadoCivil().toString());
            ps.setString(10, obj.getNacionalidade());
            ps.setString(11, obj.getCep());
            ps.setString(12, obj.getEndereco());
            ps.setString(13, obj.getNumero());
            ps.setString(14, obj.getBairro());
            ps.setString(15, obj.getComplemento());
            ps.setString(16, obj.getCidade());
            ps.setString(17, obj.getUf());
            ps.setDouble(18, obj.getPretensaoSalarial());

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
    public void update(Candidato obj) {
        
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE candidato SET "
                    + "codigo = ?, nome = ?, cpf = ?, sexo = ?, email = ?, telefone = ?, dataNascimento = ?, rg = ?, estadoCivil = ?, "
                    + "nacionalidade = ?, deficiencia = NULL, cep = ?, endereco = ?, numero = ?, bairro = ?, complemento = ?, cidade = ?,"
                    + " uf = ?, pretensaoSalarial = ? "
                    + "WHERE (id = ?)");
            
            ps.setString(1, obj.getCodigo());
            ps.setString(2, obj.getNome());
            ps.setString(3, obj.getCpf());
            ps.setString(4, obj.getSexo().toString());
            ps.setString(5, obj.getEmail());
            ps.setString(6, obj.getTelefone());
            ps.setString(7, Data.dateBanco(obj.getDataNascimento()));
            ps.setString(8, obj.getRg());
            ps.setString(9, obj.getEstadoCivil().toString());
            ps.setString(10, obj.getNacionalidade());
            ps.setString(11, obj.getCep());
            ps.setString(12, obj.getEndereco());
            ps.setString(13, obj.getNumero());
            ps.setString(14, obj.getBairro());
            ps.setString(15, obj.getComplemento());
            ps.setString(16, obj.getCidade());
            ps.setString(17, obj.getUf());
            ps.setDouble(18, obj.getPretensaoSalarial());

            ps.setInt(19, obj.getId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            Erro.mensagem(e);
        }

        DAOListener.getDAOFactory().closeConn(conn);
        
    }

    @Override
    public void delete(Candidato obj) {
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidato WHERE (id = ?)");
            ps.setInt(1, obj.getId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Candidato (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
    }

    @Override
    public void delete(Integer id) {
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidato WHERE (id = ?)");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Problemas no 'delete' de Candidado (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);   
    }

    @Override
    public List<Candidato> listAll() {
        
        List<Candidato> list = new ArrayList<>();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidato ORDER BY id");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Candidato e = new Candidato();
                e.setId(rs.getInt("id"));
                e.setCodigo(rs.getString("codigo"));
                e.setNome(rs.getString("nome"));
                e.setCpf(rs.getString("cpf"));
                e.setSexo(Sexo.valueOf(rs.getString("sexo")));
                e.setEmail(rs.getString("email"));
                e.setTelefone(rs.getString("telefone"));
                e.setDataNascimento(rs.getDate("dataNascimento"));
                e.setRg(rs.getString("rg"));
                e.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estadoCivil")));
                e.setNacionalidade(rs.getString("nacionalidade"));
                e.setCep(rs.getString("cep"));
                e.setEndereco(rs.getString("endereco"));
                e.setNumero(rs.getString("numero"));
                e.setBairro(rs.getString("bairro"));
                e.setComplemento(rs.getString("complemento"));
                e.setCidade(rs.getString("cidade"));
                e.setUf(rs.getString("uf"));
                e.setPretensaoSalarial(rs.getDouble("pretensaoSalarial"));

                list.add(e);
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Problemas no 'listAll' de Candidato (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
        
        return list;    
    
    }

    @Override
    public Candidato listByKey(Integer id) {
        
        Candidato candidato = new Candidato();
        Connection conn = DAOListener.getDAOFactory().openConn();

        try {

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM candidato WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                candidato.setId(rs.getInt("id"));
                candidato.setCodigo(rs.getString("codigo"));
                candidato.setNome(rs.getString("nome"));
                candidato.setCpf(rs.getString("cpf"));
                candidato.setSexo(Sexo.valueOf(rs.getString("sexo")));
                candidato.setEmail(rs.getString("email"));
                candidato.setTelefone(rs.getString("telefone"));
                candidato.setDataNascimento(rs.getDate("dataNascimento"));
                candidato.setRg(rs.getString("rg"));
                candidato.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estadoCivil")));
                candidato.setNacionalidade(rs.getString("nacionalidade"));
                candidato.setCep(rs.getString("cep"));
                candidato.setEndereco(rs.getString("endereco"));
                candidato.setNumero(rs.getString("numero"));
                candidato.setBairro(rs.getString("bairro"));
                candidato.setComplemento(rs.getString("complemento"));
                candidato.setCidade(rs.getString("cidade"));
                candidato.setUf(rs.getString("uf"));
                candidato.setPretensaoSalarial(rs.getDouble("pretensaoSalarial"));
            }
            
            rs.close();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("Problemas no 'listById' de CAndidato (MySQL).");
            System.out.println(e.getMessage());
        }

        DAOListener.getDAOFactory().closeConn(conn);
        
        return candidato;
    }
    
}
