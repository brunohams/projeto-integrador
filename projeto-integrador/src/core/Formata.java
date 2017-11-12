package core;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class Formata
{

    /**
     * Formata uma string
     * @param valor String
     * @param formato String
     * @return String
     */
    public static String formata (String valor, String formato)
    {

        String retorno = null;
        MaskFormatter mask;

        try
        {
            mask = new MaskFormatter(formato);
            mask.setValueContainsLiteralCharacters(false);

            retorno = mask.valueToString(valor);
        }
        catch (ParseException e)
        {
            Erro.mensagem(e);
        }

        return retorno;

    }

    /**
     * Remove todos os caracteres especiais (. , - espaços)
     * @param valor String
     * @return String
     */
    public static String removeCaracterEspecial (String valor)
    {

        return valor.replaceAll("[^a-zA-Z0-9]+","");

    }

    /**
     * Retorna somente os números da String informada
     * @param valor String
     * @return String
     */
    public static String somenteNumero (String valor)
    {

        return valor.replaceAll("[^0-9]+","");

    }

    /**
     * Retorna somente as letras da String informada
     * @param valor String
     * @return String
     */
    public static String somenteAlfanumericos (String valor)
    {

        return valor.replaceAll("[^a-zA-Z]+","");

    }

    /**
     * Formata um CPF
     * @param cpf String
     * @return String
     */
    public static String cpf (String cpf)
    {

        return formata(cpf,"###.###.###-##");

    }

    /**
     * Formata um CEP
     * @param cep String
     * @return String
     */
    public static String cep (String cep)
    {

        return formata(cep, "#####-###");

    }

}
