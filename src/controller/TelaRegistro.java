package controller;

import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import factory.DAOListener;
import java.util.HashMap;
import model.Area;

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

        candidatoDAO.insert(candidato);

        
    }


}
