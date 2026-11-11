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

    public void isolarVertice(T dado){
        Vertice<T> vertice = this.getVertice(dado);
        
        if(vertice == null) return ;

        for(Aresta<T> aresta : vertice.getArestasSaida()){
            aresta.getFim().getArestasEntrada().remove(aresta);
            this.arestas.remove(aresta);
        }
        for(Aresta<T> aresta : vertice.getArestasEntrada()){
            aresta.getInicio().getArestasSaida().remove(aresta);
            this.arestas.remove(aresta);

        }
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

        marcados.add(inicio);
        fila.add(inicio);
        distancias.add(0);

        while(fila.size() > 0){
            Vertice<T> visitado = fila.get(0);
            int distanciaVisitado = distancias.get(0);

            if(visitado == alvo){
                return distanciaVisitado;
            }

            for(int i = 0; i < visitado.getArestasSaida().size(); i++){
                Vertice<T> proximo = visitado.getArestasSaida().get(i).getFim();
                if(!marcados.contains(proximo)){
                    marcados.add(proximo);
                    fila.add(proximo);
                    distancias.add(distanciaVisitado + 1);
                }
            }
            fila.remove(0);
            distancias.remove(0);
        }
        return -1;

    }

}
