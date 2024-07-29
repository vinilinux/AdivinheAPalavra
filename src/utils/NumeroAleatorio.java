package utils;

import java.util.ArrayList;
import java.util.Random;

public class NumeroAleatorio {

    private ArrayList<Integer> numerosUtilizados = new ArrayList<>();
    private Random random = new Random();
    private int numeroAleatorio;

    public int gerarNumeroAleatorio(int min, int max) {

        if (numerosUtilizados.size() > max) {
            throw new IndexOutOfBoundsException("Limite de número utilizadaos atingidos");
        }

         numeroAleatorio = random.nextInt((max - min) + 1) + min;

        if (numerosUtilizados.contains(numeroAleatorio)) {
            gerarNumeroAleatorio(min, max);
        }else {
            numerosUtilizados.add(numeroAleatorio);
        }

        return numeroAleatorio;
    }
}
