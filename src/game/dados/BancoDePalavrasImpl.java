package game.dados;

import utils.NumeroAleatorio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class BancoDePalavrasImpl implements BancoDePalavras{

    private NumeroAleatorio numeroAleatorio = new NumeroAleatorio();

    @Override
    public String[] palavraAleatoria() throws Exception {

        String path = "files/palavras";
        String[] listaPalavras = new String[20];

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine();

            while (line != null) {
                listaPalavras[numeroAleatorio.gerarNumeroAleatorio(0, listaPalavras.length - 1)] = line;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }

        return listaPalavras;
    }

}
