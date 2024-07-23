package main;

import utils.NumeroAleatorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BancoDePalavrasImpl implements BancoDePalavras{

    private ArrayList<String> listaPalavras = new ArrayList<>();
    private NumeroAleatorio numeroAleatorio = new NumeroAleatorio();

    @Override
    public String palavraAleatoria(String nomeDoArquivo) throws Exception {

        String path = "files/" + nomeDoArquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                listaPalavras.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }



        return listaPalavras.get(numeroAleatorio.gerarNumeroAleatorio(0,listaPalavras.size()));
    }

}
