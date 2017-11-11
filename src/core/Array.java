package core;

import java.util.ArrayList;

public class Array
{

    /**
     * Exibe todos os conteúdos de um array
     * @param array Array
     */
    public static void print (ArrayList array)
    {

        for (Object valor : array)
        {
            System.out.println(valor.toString());
        }

    }

    public static void print (Object[] array)
    {

        for (int i = 0; i < array.length; i++)
        {
            System.out.println("["+i+"] -> "+array[i]);
        }

    }

}
