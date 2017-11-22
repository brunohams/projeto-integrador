package controller;

import dao.core.AreaDAO;
import dao.core.CandidatoDAO;
import factory.DAOListener;
import model.Area;

public class TelaRegistro
{

    /**
     * Salva um candidato
     */
    public final void gravaCandidato()
    {

         //Insere candidado
        //TODO - criar o dao do candidato e setar os campos para salvar
        CandidatoDAO candidatoDAO = DAOListener.getDAOFactory().getCadidatoDao();
        
        model.Candidato candidato = new model.Candidato();

        candidatoDAO.insert(candidato);

        
    }


}
