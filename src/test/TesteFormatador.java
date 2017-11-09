package test;

import core.Formata;

public class TesteFormatador
{

    public static void main(String[] args)
    {

        String cpf = "8905. ,., -51af.,sdfasdfa90";

        System.out.println(Formata.removeCaracterEspecial(cpf));

    }
}
