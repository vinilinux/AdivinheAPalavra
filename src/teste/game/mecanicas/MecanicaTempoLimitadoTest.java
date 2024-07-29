package teste.game.mecanicas;

import game.dados.BancoDePalavras;
import game.embaralhadores.Embaralhador;
import game.embaralhadores.EmbaralhadorFacil;
import game.embaralhadores.FabricaEmbaralhadores;
import game.mecanicas.MecanicaJogo;
import game.mecanicas.MecanicaTempoLimitado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class MecanicaTempoLimitadoTest {
    MecanicaJogo mecanicaJogo;
    Embaralhador mockEmbaralhador;
    BancoDePalavras mockFonte;
    ArrayList<Integer> pontos;

    private FabricaEmbaralhadores mockFactory;

    @BeforeEach
    void setUp() throws Exception {
        mockFonte = mock(BancoDePalavras.class);
        when(mockFonte.palavraAleatoria()[anyInt()]).thenReturn("Esperança");

        mockFactory = mock(FabricaEmbaralhadores.class);
        mockEmbaralhador = mock(EmbaralhadorFacil.class);
        pontos = new ArrayList<>();
        when(mockFactory.getEmbaralhador()).thenReturn(mockEmbaralhador);
        when(mockEmbaralhador.embaralharPalavra(anyString())).thenReturn("Easpenrça");

        mecanicaJogo = new MecanicaTempoLimitado(mockFonte, mockFactory, 60000,pontos);
        mecanicaJogo.iniciaJogo();
    }

    @Test
    @DisplayName("Teste de mostrar uma palavra embaralhada e se o cronograma está ativo")
    void testeDeMostrarUmaPalavraEmbaralhadaEOCronometro() {
        // Given / Arrange
        verify(mockFactory).getEmbaralhador();
        // When / Act
        String palavraEmbaralhada = mecanicaJogo.getPalavraEmbaralhada();

        // Then / Assert
        assertEquals("Easpenrça", palavraEmbaralhada);
        assertTrue(mecanicaJogo.isPartidaTerminou());
    }

    @Test
    @DisplayName("Teste verificar tentativa certa e se o cronograma parou")
    void testeVerificarTentativa() throws Exception {
        // Given / Arrange
        // When / Act
        verify(mockFonte).palavraAleatoria();

        // Then / Assert
        assertTrue(mecanicaJogo.verificarTentativa("Esperança"));
        assertFalse(mecanicaJogo.isPartidaTerminou());

    }

    @Test
    @DisplayName("Teste verificar tentativa falsa")
    void testeVerificarTentativaFalsa() throws Exception {
        // Given / Arrange
        // When / Act
        verify(mockFonte).palavraAleatoria();
        // Then / Assert
        assertFalse(mecanicaJogo.verificarTentativa("teste"));
        assertTrue(mecanicaJogo.isPartidaTerminou());

    }

    @Test
    @DisplayName("Teste se o jogo terminou após 20 palavras")
    void testeSeOJogoTerminouApós20Palavras() {
        // Given / Arrange
        when(mecanicaJogo.isJogoTerminado()).thenReturn(true);
        // When / Act
        // Then / Assert
        assertTrue(mecanicaJogo.isJogoTerminado());

    }

    @Test
    @DisplayName("Teste se o jogo terminou antes de 20 palavras")
    void testeSeOJogoTerminouAntes20Palavras() {
        // Given / Arrange
        when(mecanicaJogo.isJogoTerminado()).thenReturn(false);
        // When / Act
        // Then / Assert
        assertFalse(mecanicaJogo.isJogoTerminado());
    }

    @Test
    @DisplayName("Teste calcular pontuacao do jogo")
    void testeCalcularPontuacaoDoJogo() {
        // Given / Arrange
        for (int i = 0; i < 20; i++) {
            pontos.add(5);
        }

        // When / Act
        int actual = mecanicaJogo.calcularPontuacao();
        // Then / Assert
        assertEquals(100, actual);

    }

}