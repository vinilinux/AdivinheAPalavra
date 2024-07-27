package teste.utils;

import org.junit.jupiter.api.Test;
import utils.NumeroAleatorio;

import static org.junit.jupiter.api.Assertions.*;

class NumeroAleatorioTest {

    @Test
    void gerarNumeroAleatorio() {
        NumeroAleatorio numeroAleatorio = new NumeroAleatorio();

        int n1 = numeroAleatorio.gerarNumeroAleatorio(0,10);
        int n2 = numeroAleatorio.gerarNumeroAleatorio(0,10);
        int n3 = numeroAleatorio.gerarNumeroAleatorio(0,10);

        assertTrue(n1 != n2);
        assertTrue(n1 != n3);
        assertTrue(n2 != n3);
    }
}