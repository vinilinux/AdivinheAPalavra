package game.pontuacao;

public enum Pontuacao {
    PRIMEIRA_TENTATIVA(20),
    SEGUNDA_TENTATIVA(15),
    TERCEIRA_TENTATIVA(5);

    private final int valor;

    Pontuacao(int valor){
        this.valor = valor;
    }

    public int getValor(){
        return valor;
    }

}
