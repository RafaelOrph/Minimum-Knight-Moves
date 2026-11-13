import java.util.HashSet;

public class Tabuleiro{
    private Grafo<String> grafo;
    private HashSet<String> bloqueadas;

    public Tabuleiro(){
        this.grafo = new Grafo<String>();
        this.bloqueadas = new HashSet<String>();
        criaVertice();
        criaAresta();
    }

    //Método para gerar nomes das casas quando o tabuleiro é montado
    private String nomeCasa(int linha, int coluna){
        char letra = (char) ('a' + coluna);
        return "" + letra + (linha + 1);
    }

    //Percorre as casas do tabuleiro adicionando um vertice para cada e nomeando
    private void criaVertice(){
        for(int linha = 0; linha < 8; linha++){
            for(int coluna = 0; coluna < 8; coluna++){
                grafo.adicionarVertice(nomeCasa(linha, coluna));

            }
        }
    }

    
    private void criaAresta(){
        //Representa os 8 possiveirs movimentos do cavalo
        int[][] movimentos = {
            { 1,  2}, { 2,  1}, { 2, -1}, { 1, -2},
            {-1, -2}, {-2, -1}, {-2,  1}, {-1,  2}
        };

        //Percorre as casas
        for(int linha = 0; linha < 8; linha ++){
            for(int coluna = 0; coluna < 8; coluna++){
                // Tenta os saltos a partir da casa atual
                for(int m = 0;m < movimentos.length; m++){
                    int novaLinha = linha + movimentos[m][0];
                    int novaColuna = coluna + movimentos[m][1];
                    // descarta saltos fora dos limites do tabuleiro
                    boolean dentro = novaLinha >= 0 && novaLinha < 8 && novaColuna >= 0 && novaColuna < 8;
                    if(dentro){
                        grafo.adicionarAresta(nomeCasa(linha, coluna), nomeCasa(novaLinha, novaColuna));
                    }
                }
            }
        }
        
    }

    public boolean casaExiste(String casa) {
        return grafo.getVertice(casa) != null;
    }

    public boolean estaBloqueada(String casa) {
        return bloqueadas.contains(casa);
    }

    public boolean bloquearCasa(String casa) {
        if (!casaExiste(casa) || estaBloqueada(casa)) {
            return false;
        }
        grafo.isolarVertice(casa);
        bloqueadas.add(casa);
        return true;
    }

    //Chama o método de Busca em largura
    public int menorNMovimentos(String origem, String destino) {
        return grafo.buscaEmLargura(origem, destino);
    }
}