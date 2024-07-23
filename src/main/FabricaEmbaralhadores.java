package main;

public class FabricaEmbaralhadores {

    public Embaralhador getEmbaralhador(String tipoEmbaralhador) {
        if (tipoEmbaralhador == null) {
            return null;
        }

        if (tipoEmbaralhador.equalsIgnoreCase("facil")){
            return new EmbaralhadorFacil();
        } else if (tipoEmbaralhador.equalsIgnoreCase("medio")) {
            return new EmbaralhadorMedio();
        } else if (tipoEmbaralhador.equalsIgnoreCase("dificil")) {
            return new EmbaralhadorDificil();
        }

        return null;

    }
}
