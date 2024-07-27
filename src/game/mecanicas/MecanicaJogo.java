package game.mecanicas;

public interface MecanicaJogo {

    void iniciaJogo();
    String getPalavraEmbaralhada();
    boolean verificarTentativa(String tentativa);
    boolean isJogoTerminado();
    int calcularPontuacao();
    boolean isPartidaTerminou();
}
