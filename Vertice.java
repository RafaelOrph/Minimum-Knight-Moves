import java.util.ArrayList;

public class Vertice<T> {
    private T dado;
    private ArrayList<Vertice<T>> arestaEntrada;
    private ArrayList<Vertice<T>> arestaSaida;

    public Vertice(T dado){
        this.dado = dado;
        this.arestaEntrada = new ArrayList<>();
        this.arestaSaida = new ArrayList<>();
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public ArrayList<Vertice<T>> getArestaEntrada() {
        return arestaEntrada;
    }

    public void setArestaEntrada(ArrayList<Vertice<T>> arestaEntrada) {
        this.arestaEntrada = arestaEntrada;
    }

    public ArrayList<Vertice<T>> getArestaSaida() {
        return arestaSaida;
    }

    public void setArestaSaida(ArrayList<Vertice<T>> arestaSaida) {
        this.arestaSaida = arestaSaida;
    }
}
