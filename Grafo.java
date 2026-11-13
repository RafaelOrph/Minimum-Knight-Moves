import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;
    private ArrayList<Aresta<T>> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice<T>>();
        this.arestas = new ArrayList<Aresta<T>>();
    }

    public void adicionarVertice(T dado){
        Vertice<T> novoVertice = new Vertice<T>(dado);
        this.vertices.add(novoVertice);
    }

    public Vertice<T> getVertice(T dado){
        for(int i = 0; i < this.vertices.size(); i++){
            if(this.vertices.get(i).getDado().equals(dado)){
                return this.vertices.get(i);
            }
        }
        return null;
    }

    public void adicionarAresta(T dadoInicio, T dadoFim){
        Vertice<T> inicio = this.getVertice(dadoInicio);
        Vertice<T> fim = this.getVertice(dadoFim);
        Aresta<T> aresta = new Aresta<T>(inicio,fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    //Tira as arestas de entrada e saida de um vertice para casas bloqueadas
    public void isolarVertice(T dado){
        Vertice<T> vertice = this.getVertice(dado);
        
        if(vertice == null) return ;

        //retira a aresta para cada movimento que sai da casa bloqueada
        for(Aresta<T> aresta : vertice.getArestasSaida()){
            aresta.getFim().getArestasEntrada().remove(aresta);
            this.arestas.remove(aresta);
        }
        //retira a aresta para cada movimento que entra na casa bloqueada
        for(Aresta<T> aresta : vertice.getArestasEntrada()){
            aresta.getInicio().getArestasSaida().remove(aresta);
            this.arestas.remove(aresta);

        }
        //esvazia a lista das proprias casas
        vertice.getArestasSaida().clear();
        vertice.getArestasEntrada().clear();    
    }


    public int buscaEmLargura(T posOrigem, T target){
        Vertice<T> inicio = this.getVertice(posOrigem);
        Vertice<T> alvo = this.getVertice(target);
        
        if(inicio == null || alvo == null){
            return -1;
        }

        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();
        ArrayList<Integer> distancias = new ArrayList<Integer>();

        // começa a busca pela casa do cavalo, a distancia dela até ela mesma é 0
        marcados.add(inicio);
        fila.add(inicio);
        distancias.add(0);

        while(fila.size() > 0){
            // pega a primeira casa da fila e sua distancia
            Vertice<T> visitado = fila.get(0);
            int distanciaVisitado = distancias.get(0);

            if(visitado == alvo){
                return distanciaVisitado;
            }
            // olha cada movimento que sai da casa visitada
            for(int i = 0; i < visitado.getArestasSaida().size(); i++){
                // casa onde o cavalo pousaria com esse movimento
                Vertice<T> proximo = visitado.getArestasSaida().get(i).getFim();
                if(!marcados.contains(proximo)){
                    marcados.add(proximo);
                    fila.add(proximo);
                    distancias.add(distanciaVisitado + 1);
                }
            }
            //sai da fila junto com a distancia
            fila.remove(0);
            distancias.remove(0);
        }
        return -1;

    }

}
