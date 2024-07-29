package main;

import game.dados.BancoDePalavras;
import game.dados.BancoDePalavrasImpl;
import game.embaralhadores.FabricaEmbaralhadores;
import game.mecanicas.FabricaMecanica;
import game.mecanicas.MecanicaJogo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FabricaMecanica fabricaMecanica = new FabricaMecanica();
        BancoDePalavras bancoDePalavras = new BancoDePalavrasImpl();
        FabricaEmbaralhadores fabricaEmbaralhadores = new FabricaEmbaralhadores();

        System.out.println("Informe o modo de jogo que deseja:" +
                "\n1 - Modo por tempo" +
                "\n2 - Modo 3 tentativas");

        int escolha = sc.nextInt();

        MecanicaJogo jogo = fabricaMecanica.getMecanica(escolha, bancoDePalavras, fabricaEmbaralhadores, 60000);

        System.out.println("Descubra a palavra embaralhada");
        jogo.iniciaJogo();
        while (!jogo.isJogoTerminado()) {
            System.out.println(jogo.getPalavraEmbaralhada());
            while (!jogo.isPartidaTerminou()) {
                String tentativa = sc.next();
               if (jogo.verificarTentativa(tentativa)) {
                   System.out.println("Parabéns você acertou");
               } else if (jogo.getStatus() < 3){
                   System.out.println("Você errou! Tente Novamente. " + jogo.getStatus() + " Tentativas.");
               }
            }
        }

        System.out.println("Total de pontos feito " + jogo.calcularPontuacao());

    }
}
