package teste.game.dados;


import game.dados.BancoDePalavras;
import game.dados.BancoDePalavrasImpl;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TesteBancoDePalavras {

    @Test
    @DisplayName("Teste Pegar uma palavra de um arquivo")
    void testPegarPalavraArquivo() {
        // Given / Arrange
        BancoDePalavras palavras = new BancoDePalavrasImpl();
        // When / Act

        String[] actual = new String[0];

        try {
            actual = palavras.palavraAleatoria();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Then / Assert
        assertNotNull(actual);
    }
}
