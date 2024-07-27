package teste;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class MecanicaTentativasLimitadasTest {
    MecanicaJogo mecanicaJogo;
    Embaralhador mockEmbaralhador;
    BancoDePalavras mockFonte;
    ArrayList<Integer> mockTentativasUtilizadas;

    private FabricaEmbaralhadores mockFactory;

    @BeforeEach
    void setUp() throws Exception {
        mockFonte = mock(BancoDePalavras.class);
        when(mockFonte.palavraAleatoria()).thenReturn("Esperança");

        mockFactory = mock(FabricaEmbaralhadores.class);
        mockEmbaralhador = mock(EmbaralhadorFacil.class);
        mockTentativasUtilizadas = new ArrayList<>();
        when(mockFactory.getEmbaralhador()).thenReturn(mockEmbaralhador);
        when(mockEmbaralhador.embaralharPalavra(anyString())).thenReturn("Easpenrça");

        mecanicaJogo = new MecanicaTentativasLimitadas(mockFonte, mockFactory, mockTentativasUtilizadas);
        mecanicaJogo.iniciaJogo();
    }

    @Test
    @DisplayName("Teste de mostrar uma palavra embaralhada")
    void testeDeMostrarUmaPalavraEmbaralhada() {
        // Given / Arrange
        verify(mockFactory).getEmbaralhador();
        // When / Act
        String palavraEmbaralhada = mecanicaJogo.getPalavraEmbaralhada();
        // Then / Assert
        assertEquals("Easpenrça", palavraEmbaralhada);
    }

    @Test
    @DisplayName("Teste verificar tentativa certa")
    void testeVerificarTentativa() throws Exception {
        // Given / Arrange
        // When / Act
        verify(mockFonte).palavraAleatoria();

        // Then / Assert
        assertTrue(mecanicaJogo.verificarTentativa("Esperança"));

    }

    @Test
    @DisplayName("Teste verificar tentativa falsa")
    void testeVerificarTentativaFalsa() throws Exception {
        // Given / Arrange
        // When / Act
        verify(mockFonte).palavraAleatoria();
        // Then / Assert
        assertFalse(mecanicaJogo.verificarTentativa("teste"));

    }

    @Test
    @DisplayName("Teste se o jogo terminou após 20 palavras")
    void testeSeOJogoTerminouApós20Palavras() {
        // Given / Arrange
       when(mockFonte.getTotalPalavrasUtilizadas()).thenReturn(20);
        // When / Act
        // Then / Assert
        assertTrue(mecanicaJogo.isJogoTerminado());

    }

    @Test
    @DisplayName("Teste se o jogo terminou antes de 20 palavras")
    void testeSeOJogoTerminouAntes20Palavras() {
        // Given / Arrange
        when(mockFonte.getTotalPalavrasUtilizadas()).thenReturn(10);
        // When / Act
        // Then / Assert
        assertFalse(mecanicaJogo.isJogoTerminado());
    }

    @Test
    @DisplayName("Teste calcular pontuacao do jogo")
    void testeCalcularPontuacaoDoJogo() {
        // Given / Arrange
        for (int i = 0; i < 20; i++) {
            mockTentativasUtilizadas.add(3);
        }

        // When / Act
        int actual = mecanicaJogo.calcularPontuacao();
        // Then / Assert
        assertEquals(100, actual);

    }


}