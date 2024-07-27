package game.embaralhadores;

import utils.NumeroAleatorio;

public class FabricaEmbaralhadores {

    NumeroAleatorio numeroAleatorio;

    public FabricaEmbaralhadores() {
        this.numeroAleatorio = new NumeroAleatorio();
    }

    public Embaralhador getEmbaralhador() {

        int numero = numeroAleatorio.gerarNumeroAleatorio(1,3);

        return switch (numero) {
            case 1 -> new EmbaralhadorFacil();
            case 2 -> new EmbaralhadorMedio();
            case 3 -> new EmbaralhadorDificil();
            default -> null;
        };
    }
}
