package game.mecanicas;

import game.dados.BancoDePalavras;
import game.embaralhadores.Embaralhador;
import game.embaralhadores.FabricaEmbaralhadores;
import game.pontuacao.Pontuacao;

import java.util.ArrayList;
import java.util.List;

public class MecanicaTentativasLimitadas implements MecanicaJogo{

    private String[] palavraCerta;
    private FabricaEmbaralhadores embaralhadores;
    private BancoDePalavras bancoDePalavras;
    private int tentativasTotal;
    private int tentativasFeitas;
    private List<Integer> tentativasUtilizadas;
    private boolean partidaTerminou;
    private int cont;

    public MecanicaTentativasLimitadas(BancoDePalavras bancoDePalavras, FabricaEmbaralhadores embaralhadores) {
        this.embaralhadores = embaralhadores;
        this.bancoDePalavras = bancoDePalavras;
        this.tentativasUtilizadas = new ArrayList<>();
        this.palavraCerta = new String[0];
        this.tentativasTotal = 3;
        this.tentativasFeitas = 0;
        this.cont = 0;
    }

    @Override
    public void iniciaJogo() {
        gerarPalavrasCerta();
    }

    private String gerarPalavraEmbaralhada(){
        Embaralhador embaralhador = embaralhadores.getEmbaralhador();
        return embaralhador.embaralharPalavra(this.palavraCerta[cont]);
    }

    private void gerarPalavrasCerta(){
        try{
            this.palavraCerta = bancoDePalavras.palavraAleatoria();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getPalavraEmbaralhada() {
        this.tentativasFeitas  = 0;
        this.partidaTerminou = false;
       return gerarPalavraEmbaralhada();
    }

    @Override
    public boolean verificarTentativa(String tentativa) {
        if (isJogoTerminado())  {
            return false;
        }
        if (this.palavraCerta[cont].equalsIgnoreCase(tentativa)) {
            tentativasUtilizadas.add(tentativasFeitas);
            partidaTerminou = true;
            cont++;
            return true;
        }
        if (tentativasFeitas == tentativasTotal) {
            tentativasUtilizadas.add(0);
            cont++;
        }
        this.tentativasFeitas++;
        return false;
    }

    @Override
    public boolean isJogoTerminado() {
        if (cont == this.palavraCerta.length - 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isPartidaTerminou(){
        if (tentativasFeitas == tentativasTotal) {
            partidaTerminou = true;
        }
        return partidaTerminou;
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

    @Override
    public int getStatus() {
        return tentativasFeitas;
    }
}
