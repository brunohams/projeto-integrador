package core;

public class Erro
{

    public static void mensagem (Exception e)
    {

        //Linha de erro
        StackTraceElement[] aLinha = Thread.currentThread().getStackTrace();

        String mensagemRetorno = "Ocorreu um erro na classe: "+e.getStackTrace()[0].getFileName()+"\n";
        mensagemRetorno += "Mensagem: "+e.getMessage()+"\n";
        mensagemRetorno += "Classe: "+e.getClass()+"\n";

        System.out.println("\033[31;1m"+mensagemRetorno+"\033[0m");

        for (StackTraceElement linha : aLinha)
        {

            System.out.println("\033[31;1m"+linha.toString()+"\033[0m");

        }

    }

}
