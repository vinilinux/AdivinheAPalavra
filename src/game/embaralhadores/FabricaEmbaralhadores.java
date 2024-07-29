package game.embaralhadores;
import java.util.Random;

public class FabricaEmbaralhadores {

    public Embaralhador getEmbaralhador() {

        Random random = new Random();

        int numero = random.nextInt(1,4);

        return switch (numero) {
            case 1 -> new EmbaralhadorFacil();
            case 2 -> new EmbaralhadorMedio();
            case 3 -> new EmbaralhadorDificil();
            default -> null;
        };
    }
}
