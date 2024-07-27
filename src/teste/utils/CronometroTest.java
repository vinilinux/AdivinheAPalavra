package teste.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Cronometro;

import static org.junit.jupiter.api.Assertions.*;

class CronometroTest {
    private Cronometro cronometro;

    @BeforeEach
    void setup() {
        cronometro = new Cronometro(6000);
    }

    @Test
    @DisplayName("Testar o cronometro")
    void testarOCronometro() {
        // Given / Arrange
        // When / Act
        cronometro.start();
        boolean teste = cronometro.isRunning();
        // Then / Assert
        assertTrue(teste);
    }

    @Test
    @DisplayName("Testar o cronometro quando o tempo acaba")
    void testarOCronometroTempoTermina() {
        // Given / Arrange
        // When / Act
        cronometro.start();
        while (cronometro.isRunning()) {
            try {
                Thread.sleep(100); // Pausa por 100 milissegundos para evitar um loop apertado
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Then / Assert
        assertFalse(cronometro.isRunning());
    }

    @Test
    @DisplayName("teste parando o cronometro")
    void testeParandoOCronometro() {
        // Given / Arrange
        // When / Act
        cronometro.start();
        assertTrue(cronometro.isRunning());
        cronometro.stop();
        assertFalse(cronometro.isRunning());
        // Then / Assert
    }

}