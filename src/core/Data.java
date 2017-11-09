package core;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe responsável por formatação de datas
 */
public class Data
{

    /**
     * Converte uma Data para string do formato desejado
     * @param data Date
     * @param formato String
     * @return string
     */
    public static String formata (Date data, String formato)
    {

        String retorno = null;

        //Converte strData para string
        SimpleDateFormat dateFormatPadrao = new SimpleDateFormat(formato);
        retorno = dateFormatPadrao.format(data);

        return retorno;

    }

    /**
     * Converte uma string para date
     */
    public static Date stringParaDate (String data)
    {

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try
        {
            date = sdf.parse(data);
        }
        catch (ParseException e)
        {
            Erro.mensagem(e);
        }

        return date;

    }


    /**
     * Converte uma data para padrao americano y-m-d
     * @param data Date
     * @return string
     */
    public static String dateBanco (Date data)
    {

        return formata(data, "yy-MM-dd");

    }

    /**
     * Converte uma data para padrao americano y-m-d hh:mm:ss
     * @param data Date
     * @return string
     */
    public static String dateTimeBanco (Date data)
    {

        return formata(data, "yy-MM-dd HH:mm:ss");

    }

    /**
     * Converte uma data para exibir
     * @param data Date
     * @return string
     */
    public static String dateExibe (Date data)
    {

        return formata(data, "dd/MM/yy");

    }

    /**
     * Converte uma data para exibir em datetime
     * @param data Date
     * @return string
     */
    public static String dateTimeExibe (Date data)
    {

        return formata(data, "dd/MM/yy HH:mm:ss");

    }

}
