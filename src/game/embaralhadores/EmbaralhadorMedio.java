package game.embaralhadores;

import utils.NumeroAleatorio;

public class EmbaralhadorMedio implements Embaralhador{

    @Override
    public String embaralharPalavra(String palavra) {

        StringBuilder palavraEmbaralhada = new StringBuilder();

        NumeroAleatorio numeroAleatorio = new NumeroAleatorio();

        palavraEmbaralhada.append(palavra.charAt(0));
        int cont = 0;
        int numero;
        while (cont != palavra.length() - 2) {
            numero = numeroAleatorio.gerarNumeroAleatorio(1, palavra.length() - 2);
            palavraEmbaralhada.append(palavra.charAt(numero));
            cont++;
        }

        palavraEmbaralhada.append(palavra.charAt(palavra.length()-1));

        return palavraEmbaralhada.toString();
    }
}
