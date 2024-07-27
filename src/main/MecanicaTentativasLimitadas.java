package main;

import java.util.ArrayList;
import java.util.List;

public class MecanicaTentativasLimitadas implements MecanicaJogo{

    private String palavraCerta;
    private String palavraEmbaralhada;
    private FabricaEmbaralhadores embaralhadores;
    private BancoDePalavras bancoDePalavras;
    private int tentativasTotal;
    private int tentativasFeitas;
    private List<Integer> tentativasUtilizadas;
    private boolean partidaTerminou;

    public MecanicaTentativasLimitadas(BancoDePalavras bancoDePalavras, FabricaEmbaralhadores embaralhadores, ArrayList<Integer> tentativasUtilizadas) {
        this.embaralhadores = embaralhadores;
        this.bancoDePalavras = bancoDePalavras;
        this.tentativasUtilizadas = tentativasUtilizadas;
        this.tentativasTotal = 3;
        this.tentativasFeitas = 1;
    }

    @Override
    public void iniciaJogo() {
        gerarPalavraCerta();
        gerarPalavraEmbaralhada();
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
    public String getStatus() {
        return "Tentativas restantes: " + (tentativasTotal - tentativasFeitas);
    }

    @Override
    public boolean verificarTentativa(String tentativa) {
        if (isJogoTerminado())  {
            return false;
        }
        if (tentativa.equalsIgnoreCase(this.palavraCerta)) {
            tentativasUtilizadas.add(tentativasFeitas);
            return true;
        }
        if (tentativasFeitas == tentativasTotal) tentativasUtilizadas.add(0);
        this.tentativasFeitas++;
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
    public boolean isPartidaTerminou(){
        if (tentativasFeitas == tentativasTotal) {
            return true;
        }
        return false;
    }

    @Override
    public int calcularPontuacao() {
        int total = 0;
        for (Integer tentativaUtilizada : tentativasUtilizadas) {
            switch (tentativaUtilizada) {
                case 1 -> total += Pontuacao.PRIMEIRA_TENTATIVA.getValor();
                case 2 -> total += Pontuacao.SEGUNDA_TENTATIVA.getValor();
                case 3 -> total += Pontuacao.TERCEIRA_TENTATIVA.getValor();
            }
        }
        return total;
    }
}
