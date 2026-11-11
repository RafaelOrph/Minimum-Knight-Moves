import java.util.Scanner;

public class Main {
    // Lê uma casa e já devolve em minúsculas e sem espaços ("E4 " vira "e4")
    public static String lerCasa(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim().toLowerCase();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();
        String origem = null;
        String destino = null;
        String opcao = "";
        String casa;

        while (!opcao.equals("0")) {
            System.out.println();
            System.out.println("===== CAVALO NO TABULEIRO =====");
            System.out.println("Cavalo: " + (origem == null ? "(nao definida)" : origem));
            System.out.println("Alvo  : " + (destino == null ? "(nao definida)" : destino));
            System.out.println("1 - Definir casa do cavalo");
            System.out.println("2 - Definir casa do alvo");
            System.out.println("3 - Adicionar obstaculo (peca aliada)");
            System.out.println("4 - Calcular menor numero de movimentos");
            System.out.println("5 - Remover todos os obstaculos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextLine().trim();

            switch (opcao) {
                case "1":
                    casa = lerCasa(sc, "Casa do cavalo (ex: a1): ");
                    if (!tabuleiro.casaExiste(casa)) {
                        System.out.println("Casa invalida. Use letra de a-h e numero de 1-8.");
                    } else if (tabuleiro.estaBloqueada(casa)) {
                        System.out.println("Essa casa tem um obstaculo.");
                    } else {
                        origem = casa;
                    }
                    break;

                case "2":
                    casa = lerCasa(sc, "Casa do alvo (ex: h8): ");
                    if (!tabuleiro.casaExiste(casa)) {
                        System.out.println("Casa invalida. Use letra de a-h e numero de 1-8.");
                    } else if (tabuleiro.estaBloqueada(casa)) {
                        System.out.println("Impossivel: essa casa tem uma peca aliada.");
                    } else {
                        destino = casa;
                    }
                    break;

                case "3":
                    casa = lerCasa(sc, "Casa do obstaculo (ex: c3): ");
                    if (!tabuleiro.casaExiste(casa)) {
                        System.out.println("Casa invalida. Use letra de a-h e numero de 1-8.");
                    } else if (casa.equals(origem) || casa.equals(destino)) {
                        System.out.println("Nao pode colocar obstaculo na casa do cavalo ou do alvo.");
                    } else if (!tabuleiro.bloquearCasa(casa)) {
                        System.out.println("Essa casa ja tem um obstaculo.");
                    } else {
                        System.out.println("Obstaculo adicionado em " + casa + ".");
                    }
                    break;

                case "4":
                    if (origem == null || destino == null) {
                        System.out.println("Defina a casa do cavalo e a do alvo primeiro.");
                    } else {
                        int movimentos = tabuleiro.menorNMovimentos(origem, destino);
                        if (movimentos == -1) {
                            System.out.println("Nao existe caminho de " + origem + " ate " + destino + ".");
                        } else {
                            System.out.println("Menor numero de movimentos de " + origem
                                    + " ate " + destino + ": " + movimentos);
                        }
                    }
                    break;

                case "5":
                    tabuleiro = new Tabuleiro();
                    System.out.println("Obstaculos removidos.");
                    break;

                case "0":
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }
        }
    }
}
