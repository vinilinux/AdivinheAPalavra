package teste;

import main.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEmbaralhadores {

    static FabricaEmbaralhadores embaralhadores;

    @BeforeAll
    static void setup() {
        embaralhadores = new FabricaEmbaralhadores();
    }

    @Test
    @DisplayName("Teste do embaralhador Facil")
    void testeDoEmbaralhadorFacil() {
        // Given / Arrange
        Embaralhador embaralhador = new EmbaralhadorFacil();
        // When / Act
       String actual = embaralhador.embaralharPalavra("Fruta");
       String expect = "aturf";
        // Then / Assert
        assertTrue(actual.equalsIgnoreCase(expect));
    }

    @Test
    @DisplayName("Teste do embaralhador medio")
    void testeDoEmbaralhadorMedio() {
        // Given / Arrange
        Embaralhador embaralhador = new EmbaralhadorMedio();
        // When / Act
        String actual = embaralhador.embaralharPalavra("apartamento");
        // Then / Assert
        System.out.println(actual);
        assertFalse(actual.isBlank());
    }

    @Test
    @DisplayName("Teste do embaralhador dificil")
    void testeDoEmbaralhadorDificil() {
        // Given / Arrange
        Embaralhador embaralhador = new EmbaralhadorDificil();
        // When / Act
        String actual = embaralhador.embaralharPalavra("Esplendor");
        // Then / Assert
        System.out.println(actual);
        assertFalse(actual.isBlank());
    }

    @Test
    @DisplayName("Teste da fabrica de embaralhador aleatorio")
    void testeDaFabricaDeEmbaralhadorAleatorio() {
        // Given / Arrange
        Embaralhador embaralhador = embaralhadores.getEmbaralhador();
        // When / Act
        String actual = embaralhador.embaralharPalavra("computador");
        // Then / Assert
        System.out.println(actual);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }


}
