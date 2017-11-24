package controller;
import java.util.Date;
import model.Candidato.EstadoCivil;
import model.Candidato.Sexo;

import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import factory.DAOListener;
import java.util.HashMap;
import java.util.Map;
import model.Area;
import util.Data;

public class TelaRegistro
{

    /**
     * Salva um candidato
     */
    public final void gravaCandidato(HashMap<String, String> aCampo)
    {

         //Insere candidado
        //TODO - criar o dao do candidato e setar os campos para salvar
        CandidatoDAO candidatoDAO = DAOListener.getDAOFactory().getCadidatoDao();
        
        model.Candidato candidato = new model.Candidato();
        candidato.setCodigo("1");
        candidato.setNome(aCampo.get("nome"));
        candidato.setCpf(aCampo.get("cpf"));
        candidato.setSexo(Sexo.valueOf(aCampo.get("sexo")));
        candidato.setEmail(aCampo.get("email"));
        candidato.setTelefone(aCampo.get("telefone"));
        candidato.setDataNascimento(Data.stringParaDate(aCampo.get("dataNascimento")));
        candidato.setRg(aCampo.get("rg"));
        candidato.setEstadoCivil(EstadoCivil.valueOf(aCampo.get("estadoCivil")));
        candidato.setNacionalidade(aCampo.get("nacionalidade"));
        candidato.setCep(aCampo.get("cep"));
        candidato.setEndereco(aCampo.get("endereco"));
        candidato.setNumero(aCampo.get("numero"));
        candidato.setBairro(aCampo.get("bairro"));
        candidato.setComplemento(aCampo.get("complemento"));
        candidato.setCidade(aCampo.get("cidade"));
        candidato.setUf(aCampo.get("uf"));
        candidato.setPretensaoSalarial(Double.parseDouble(aCampo.get("pretensaoSalarial")));

        candidatoDAO.insert(candidato);

    }


}
