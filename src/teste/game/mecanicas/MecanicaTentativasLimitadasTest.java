package teste.game.mecanicas;

import game.dados.BancoDePalavras;
import game.dados.BancoDePalavrasImpl;
import game.embaralhadores.Embaralhador;
import game.embaralhadores.EmbaralhadorFacil;
import game.embaralhadores.FabricaEmbaralhadores;
import game.mecanicas.MecanicaJogo;
import game.mecanicas.MecanicaTentativasLimitadas;
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
        when(mockFonte.palavraAleatoria()).thenReturn(new String[]{"Esperança"});

        mockFactory = mock(FabricaEmbaralhadores.class);
        mockEmbaralhador = mock(EmbaralhadorFacil.class);
        mockTentativasUtilizadas = new ArrayList<>();
        when(mockFactory.getEmbaralhador()).thenReturn(mockEmbaralhador);
        when(mockEmbaralhador.embaralharPalavra(anyString())).thenReturn("Easpenrça");

        mecanicaJogo = new MecanicaTentativasLimitadas(mockFonte, mockFactory);
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

}