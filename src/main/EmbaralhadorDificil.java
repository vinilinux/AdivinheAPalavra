package main;

import utils.NumeroAleatorio;

public class EmbaralhadorDificil implements Embaralhador{

    StringBuilder palavraEmbaralhada;
    NumeroAleatorio numeroAleatorio;
    public EmbaralhadorDificil() {
        palavraEmbaralhada = new StringBuilder();
        numeroAleatorio = new NumeroAleatorio();
    }

    @Override
    public String embaralharPalavra(String palavra) {

        for (int i =0; i < palavra.length(); i++) {
            palavraEmbaralhada.append(palavra.charAt(numeroAleatorio.gerarNumeroAleatorio(0, palavra.length() - 1)));
            if (i == 3) {
                palavraEmbaralhada.append("$");
            }
        }

        return palavraEmbaralhada.toString();
    }
}
