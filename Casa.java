public class Casa {
    private final int linha,coluna,indice;

    public Casa(int linha, int coluna, int n) {
        this.linha = linha;
        this.coluna = coluna;
        this.indice = linha * n + coluna;


    }

    @Override
    public String toString() {
        return "" + (char) ('a' + coluna) + (linha + 1);
    }
}
