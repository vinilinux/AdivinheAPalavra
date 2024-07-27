package game.mecanicas;

import game.dados.BancoDePalavras;
import game.embaralhadores.Embaralhador;
import game.embaralhadores.FabricaEmbaralhadores;
import utils.Cronometro;

import java.util.ArrayList;
import java.util.List;

public class MecanicaTempoLimitado implements MecanicaJogo{

    private String palavraCerta;
    private String palavraEmbaralhada;
    private FabricaEmbaralhadores embaralhadores;
    private BancoDePalavras bancoDePalavras;
    private Cronometro cronometro;
    private List<Integer> pontos;

    public MecanicaTempoLimitado(BancoDePalavras bancoDePalavras, FabricaEmbaralhadores embaralhadores, long totalMillis, ArrayList<Integer> pontos) {
        this.embaralhadores = embaralhadores;
        this.bancoDePalavras = bancoDePalavras;
        cronometro = new Cronometro(totalMillis);
        this.pontos = pontos;
    }

    @Override
    public void iniciaJogo() {
        gerarPalavraCerta();
        gerarPalavraEmbaralhada();
        cronometro.start();
    }

    private void gerarPalavraEmbaralhada(){
        Embaralhador embaralhador = embaralhadores.getEmbaralhador();
        this.palavraEmbaralhada = embaralhador.embaralharPalavra(this.palavraCerta);
    }

    private void gerarPalavraCerta(){
        try{
            this.palavraCerta = bancoDePalavras.palavraAleatoria();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getPalavraEmbaralhada() {
        return this.palavraEmbaralhada;
    }

    @Override
    public boolean verificarTentativa(String tentativa) {
        if (isJogoTerminado()) return false;
        if (tentativa.equalsIgnoreCase(this.palavraCerta)) {
           cronometro.stop();
           pontos.add(5);
            return true;
        }
        return false;
    }

    @Override
    public boolean isJogoTerminado() {
        if (bancoDePalavras.getTotalPalavrasUtilizadas() == 20) {
            return true;
        }
        return false;
    }

    @Override
    public int calcularPontuacao() {
        int total = 0;
        for (Integer ponto : pontos) {
            total += ponto;
        }
        return total;
    }

    @Override
    public boolean isPartidaTerminou() {
        return cronometro.isRunning();
    }
}
