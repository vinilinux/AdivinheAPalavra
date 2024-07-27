package game.mecanicas;


import game.dados.BancoDePalavras;
import game.embaralhadores.FabricaEmbaralhadores;

import java.util.ArrayList;

public class FabricaMecanica {

    public MecanicaJogo getMecanica(int escolha, BancoDePalavras bancoDePalavras, FabricaEmbaralhadores embaralhadores, long milisegundos) {

        return switch (escolha) {
            case 1 -> new MecanicaTempoLimitado(bancoDePalavras, embaralhadores,milisegundos, new ArrayList<>());
            case 2 -> new MecanicaTentativasLimitadas(bancoDePalavras, embaralhadores);
            default -> throw new IllegalArgumentException("Escolha inválida para mecânica de jogo: " + escolha);
        };
    }
}
