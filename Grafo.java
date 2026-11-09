import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> lista;
    private ArrayList<Vertice<T>> arestas;

    public Grafo(ArrayList<Vertice<T>> lista, ArrayList<Vertice<T>> arestas) {
        this.lista = lista;
        this.arestas = arestas;
    }

    public void adicionarVertice(Vertice<T> vertice) {
        this.lista.add(vertice);
    }
    public Vertice<T> getVertice(Vertice<T> vertice) {
        return lista.get(lista.indexOf(vertice));
    }

    public void adicionarAresta(Vertice<T> inicio, Vertice<T>fim){
        inicio.getArestaSaida().add(fim);
        fim.getArestaEntrada().add(inicio);
    }



}
