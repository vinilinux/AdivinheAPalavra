package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BancoDePalavrasImpl implements BancoDePalavras{

    private ArrayList<String> listaPalavras = new ArrayList<>();
    private ArrayList<Integer> numerosUtilizados = new ArrayList<>();

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

        return listaPalavras.get(gerarNumeroAleatorio());
    }

    private int gerarNumeroAleatorio() throws Exception {

        Random random = new Random();

        int numeroAleatorio = random.nextInt(20);

       if (numerosUtilizados.contains(numeroAleatorio)) {
           gerarNumeroAleatorio();
       }

        if (numerosUtilizados.size() == 20) {
            throw new Exception("Acabou a lista de palavras");
        }

        numerosUtilizados.add(numeroAleatorio);

        return numeroAleatorio;

    }
}
