package main;

public class EmbaralhadorFacil implements Embaralhador {
    @Override
    public String embaralharPalavra(String palavra) {

        StringBuilder palavraInvertida = new StringBuilder();

        for (int i = palavra.length() - 1; i >= 0; i--)  {
            palavraInvertida.append(palavra.charAt(i));
        }

        return palavraInvertida.toString();
    }
}
