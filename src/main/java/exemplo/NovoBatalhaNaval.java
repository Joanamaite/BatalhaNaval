package exemplo;

import java.util.Scanner;
import java.util.Random;

public class NovoBatalhaNaval {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        char[][] TabuleiroJogador1 = new char[10][10];
        char[][] TabuleiroJogador2 = new char[10][10];
        char[][] tabuleiroMarcadoJogador1 = new char[10][10];
        char[][] tabuleiroMarcadoJogador2 = new char[10][10];
        boolean contraMaquina = false;
        boolean contraum = false;

        System.out.println("Selecione o modo de jogo:");
        System.out.println("1 - Dois jogadores");
        System.out.println("2 - Contra a máquina");

        boolean entradaValida = false;

        while (!entradaValida) {
            int modoJogo = ler.nextInt();

            switch (modoJogo) {
                case 1:
                    contraum = true;
                    entradaValida = true;
                    break;
                case 2:
                    contraMaquina = true;
                    entradaValida = true;
                    break;
                default:
                    System.out.println("Número inválido. Digite novamente.");
                    break;
            }
        }
        System.out.println("Deseja alocar automaticamente (1)Sim (2)Não: ");
        int automatico = ler.nextInt();
        if (contraum) {
            if (automatico == 1) {
                alocarNaviosAutomaticamente(TabuleiroJogador1);
                System.out.println("Tabuleiro Jogador 1 ");
                exibirTabuleiro(TabuleiroJogador1);
                alocarNaviosAutomaticamente(TabuleiroJogador2);
                System.out.println("Tabuleiro Jogador 2 ");
                exibirTabuleiro(TabuleiroJogador2);
            }
            if (automatico == 2) {
                posicionarNavios(TabuleiroJogador1, ler);
                posicionarNavios(TabuleiroJogador2, ler);
            }

        }
        if (contraMaquina) {
            if (automatico == 1) {
                alocarNaviosAutomaticamente(TabuleiroJogador1);
                System.out.println("Tabuleiro Jogador 1 ");
                exibirTabuleiro(TabuleiroJogador1);
                alocarNaviosAutomaticamente(TabuleiroJogador2);//Máquina
                System.out.println("Tabuleiro máquina");
                exibirTabuleiro(TabuleiroJogador2);
            }
            if (automatico == 2) {
                posicionarNavios(TabuleiroJogador1, ler);
                alocarNaviosAutomaticamente(TabuleiroJogador2);
                System.out.println("Tabuleiro máquina");
                exibirTabuleiro(TabuleiroJogador2);
            }
        }

        System.out.println("Início do jogo!");
        boolean jogoFinalizado = false;

        while (!jogoFinalizado) {
            // Jogador 1 - Seu turno
              realizarJogada(TabuleiroJogador2, tabuleiroMarcadoJogador2, ler, "Jogador 1");


                if (verificarVitoria(tabuleiroMarcadoJogador2)) {
                    System.out.println("O jogador 1 venceu!");
                    jogoFinalizado = true;
                }
           

            if (contraMaquina) {
                realizarJogadaMaquina(TabuleiroJogador2, tabuleiroMarcadoJogador1);
            } else {
                // Jogador 2 - Seu turno
             
                   realizarJogada(TabuleiroJogador2, tabuleiroMarcadoJogador2, ler, "Jogador 2");


                    if (verificarVitoria(tabuleiroMarcadoJogador1)) {
                        System.out.println("O jogador 2 venceu!");
                        jogoFinalizado = true;
                    }
                
            }
        }

        System.out.println("Fim de jogo");
    }
public static void realizarJogada(char[][] tabuleiro, char[][] tabuleiroMarcado, Scanner ler, String jogador) {
    boolean jogadaValida = false;

    while (!jogadaValida) {
        System.out.println(jogador + " - Seu turno!");
        int linha, coluna;
        String coluna2;

        do {
            System.out.println("Informe a linha (0 a 9): ");
            linha = ler.nextInt();
            System.out.println("Informe a coluna (A a J): ");
            coluna2 = ler.next().toUpperCase();
            String alfabeto = "ABCDEFGHIJ";
            coluna = alfabeto.indexOf(coluna2);

            if (linha > 9 || linha < 0 || coluna > 9 || coluna < 0) {
                System.out.println("Números inválidos. Digite novamente.");
            }
        } while (linha > 9 || linha < 0 || coluna > 9 || coluna < 0);

        // Verifica se a jogada acertou um navio
        if (tabuleiro[linha][coluna] == 'N') {
            System.out.println("Você acertou um navio!");
            tabuleiroMarcado[linha][coluna] = 'X'; // Marca o acerto no tabuleiro marcado
            exibirTabuleiro(tabuleiroMarcado);
            jogadaValida = true;
        } else if (tabuleiroMarcado[linha][coluna] == 'A') {
            System.out.println("Você já jogou nesta posição.");
        } else {
            tabuleiroMarcado[linha][coluna] = 'A';
            System.out.println("Você não acertou nenhum navio.");// Marca a água no tabuleiro marcado
            jogadaValida = true;
        }
    }
}

    public static boolean verificarVitoria(char[][] tabuleiroMarcado) {
        int countX = 0;

        for (int i = 0; i < tabuleiroMarcado.length; i++) {
            for (int j = 0; j < tabuleiroMarcado[i].length; j++) {
                if (tabuleiroMarcado[i][j] == 'X') {
                    countX++;
                }
            }
        }

        return countX == 20; // Verifica se há 20 'X' no tabuleiro marcado

    }

    // Método para realizar uma jogada da máquina
    public static void realizarJogadaMaquina(char[][] tabuleiro, char[][] tabuleiroMarcado) {
        Random random = new Random();
        int tamanho = tabuleiro.length;//tamanho do tabueleiro para dar números aleatorios ate seu tamanho

        // Escolher uma posição aleatória para atacar
        int linha = random.nextInt(tamanho);
        int coluna = random.nextInt(tamanho);

        // Verificar se a jogada acertou um navio
        if (tabuleiro[linha][coluna] == 'N') {
            System.out.println("A máquina acertou um navio!");

            // Marcar o acerto no tabuleiro marcado
            tabuleiroMarcado[linha][coluna] = 'X';
            exibirTabuleiro(tabuleiroMarcado);
        } else {
            tabuleiroMarcado[linha][coluna] = 'A';
            System.out.println("A máquina não acertou nenhum navio.");
        }
    }

    // Método para exibir o tabuleiro
    public static void exibirTabuleiro(char[][] Tabuleiro) {
        char Espaco = ' ';
        int tamanho = 10;
        System.out.print("        ");
        for (int i = 0; i < tamanho; i++) {
            char coluna = (char) ('A' + i); // Converter o valor numérico da coluna em letra
            System.out.print(coluna + "    \t");
        }
        System.out.println();

        for (int i = 0; i < tamanho; i++) {
            System.out.print((i + 0) + "\t");
            for (int j = 0; j < tamanho; j++) {
                if (Tabuleiro[i][j] == Espaco) { // Verifica se a célula está vazia
                    System.out.print("\t");
                } else {
                    System.out.print(Tabuleiro[i][j] + "\t");
                }
            }
            System.out.println("");
            System.out.println("   ---------------------------------------------------------------------------------");
        }
    }

    // Método para posicionar os navios no tabuleiro
    public static void posicionarNavios(char[][] tabuleiro, Scanner ler) {
        int Navioum = 4;
        int Naviodois = 3;
        int Naviotres = 2;
        int Navioquatro = 1;
        int TamanhoNavioum = 1;
        int TamanhoNaviodois = 2;
        int TamanhoNaviotres = 3;
        int TamanhoNavioquatro = 4;
        int totalBarcos = 10; // Número total de navios a serem posicionados
        int tamanhoNavio = 4; // Tamanho de cada navio

        while (totalBarcos > 0) {
            System.out.printf("(1) %d barcos de 1 posição\n", Navioum);
            System.out.printf("(2) %d barcos de 2 posição\n", Naviodois);
            System.out.printf("(3) %d barcos de 3 posição\n", Naviotres);
            System.out.printf("(4) %d barcos de 4 posição\n", Navioquatro);
            System.out.printf("Você tem %d para colocar ainda\n", totalBarcos);
            System.out.println("Escolha qual navio você quer alocar: ");
            int alocar = ler.nextInt();
            switch (alocar) {
                case 1:
                    System.out.println("Informe a linha da posição inicial: ");
                    int linha = ler.nextInt();
                    System.out.println("Informe a coluna da posição inicial: ");
                    String coluna2 = ler.next().toUpperCase();
                    String alfabeto = "ABCDEFGHIJ";
                    int coluna = alfabeto.indexOf(coluna2);

                    if (coluna + TamanhoNavioum > tabuleiro[0].length) {
                        System.out.println("Tamanho excedido. Tente novamente.");
                        break;
                    }

                    boolean ocupado = false;
                    for (int i = coluna; i < coluna + TamanhoNavioum; i++) {
                        if (tabuleiro[linha][i] == 'N') {
                            ocupado = true;
                            break;
                        }
                    }
                    if (ocupado) {
                        System.out.println("Essa posição já está ocupada. Tente novamente.");
                        break;
                    }

                    for (int i = coluna; i < coluna + TamanhoNavioum; i++) {
                        tabuleiro[linha][i] = 'N'; // 'N' representa um navio
                        Navioum--;
                    }

                    totalBarcos--;
                    exibirTabuleiro(tabuleiro);
                    break;

                case 2:

                    System.out.println("Informe a linha da posição inicial: ");
                    linha = ler.nextInt();
                    System.out.println("Informe a coluna da posição inicial: ");
                    coluna2 = ler.next().toUpperCase();
                    alfabeto = "ABCDEFGHIJ";
                    coluna = alfabeto.indexOf(coluna2);
                    System.out.println("Posicionar o navio na horizontal (H) ou vertical (V)?");
                    String orientacao = ler.next();

                    if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
                        System.out.println("Posição inválida. Tente novamente.");
                        break;
                    }

                    if (orientacao.equalsIgnoreCase("H")) {
                        if (coluna + TamanhoNaviodois > tabuleiro[0].length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        ocupado = false;
                        for (int i = coluna; i < coluna + TamanhoNaviodois; i++) {
                            if (tabuleiro[linha][i] == 'N') {

                                ocupado = true;
                                break;
                            }
                        }

                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = coluna; i < coluna + TamanhoNaviodois; i++) {
                            tabuleiro[linha][i] = 'N'; // 'N' representa um navio

                        }
                        Naviodois--;
                    } else if (orientacao.equalsIgnoreCase("V")) {
                        if (linha + TamanhoNaviodois > tabuleiro.length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        ocupado = false;
                        for (int i = linha; i < linha + TamanhoNaviodois; i++) {
                            if (tabuleiro[i][coluna] == 'N') {
                                ocupado = true;
                                break;
                            }
                        }

                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = linha; i < linha + TamanhoNaviodois; i++) {
                            tabuleiro[i][coluna] = 'N'; // 'N' representa um navio

                        }
                        Naviodois--;
                    }

                    totalBarcos--;
                    exibirTabuleiro(tabuleiro);

                    break;

                case 3:

                    System.out.println("Informe a linha da posição inicial: ");
                    linha = ler.nextInt();
                    System.out.println("Informe a coluna da posição inicial: ");
                    coluna2 = ler.next().toUpperCase();
                    alfabeto = "ABCDEFGHIJ";
                    coluna = alfabeto.indexOf(coluna2);
                    System.out.println("Posicionar o navio na horizontal (H) ou vertical (V)?");
                    orientacao = ler.next();

                    if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
                        System.out.println("Posição inválida. Tente novamente.");
                        break;
                    }

                    if (orientacao.equalsIgnoreCase("H")) {
                        if (coluna + TamanhoNaviotres > tabuleiro[0].length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        ocupado = false;
                        for (int i = coluna; i < coluna + TamanhoNaviotres; i++) {
                            if (tabuleiro[linha][i] == 'N') {
                                ocupado = true;
                                break;
                            }
                        }

                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = coluna; i < coluna + TamanhoNaviotres; i++) {
                            tabuleiro[linha][i] = 'N'; // 'N' representa um navio

                        }
                        Naviotres--;
                    } else if (orientacao.equalsIgnoreCase("V")) {
                        if (linha + TamanhoNaviotres > tabuleiro.length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        ocupado = false;
                        for (int i = linha; i < linha + TamanhoNaviotres; i++) {
                            if (tabuleiro[i][coluna] == 'N') {
                                ocupado = true;
                                break;
                            }
                        }

                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = linha; i < linha + TamanhoNaviotres; i++) {
                            tabuleiro[i][coluna] = 'N'; // 'N' representa um navio

                        }
                        Naviotres--;
                    }

                    totalBarcos--;
                    exibirTabuleiro(tabuleiro);

                    break;

                case 4:

                    System.out.println("Informe a linha da posição inicial: ");
                    linha = ler.nextInt();
                    System.out.println("Informe a coluna da posição inicial: ");
                    coluna2 = ler.next().toUpperCase();
                    alfabeto = "ABCDEFGHIJ";
                    coluna = alfabeto.indexOf(coluna2);
                    System.out.println("Posicionar o navio na horizontal (H) ou vertical (V)?");
                    orientacao = ler.next();

                    if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
                        System.out.println("Posição inválida. Tente novamente.");
                        break;
                    }

                    if (orientacao.equalsIgnoreCase("H")) {
                        if (coluna + TamanhoNavioquatro > tabuleiro[0].length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        ocupado = false;
                        for (int i = coluna; i < coluna + TamanhoNavioquatro; i++) {
                            if (tabuleiro[linha][i] == 'N') {
                                ocupado = true;
                                break;
                            }
                        }

                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = coluna; i < coluna + TamanhoNavioquatro; i++) {
                            tabuleiro[linha][i] = 'N'; // 'N' representa um navio

                        }
                        Navioquatro--;
                    } else if (orientacao.equalsIgnoreCase("V")) {
                        if (linha + TamanhoNavioquatro > tabuleiro.length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        ocupado = false;
                        for (int i = linha; i < linha + TamanhoNavioquatro; i++) {
                            if (tabuleiro[i][coluna] == 'N') {
                                ocupado = true;
                                break;
                            }
                        }

                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = linha; i < linha + TamanhoNavioquatro; i++) {
                            tabuleiro[i][coluna] = 'N'; // 'N' representa um navio

                        }
                        Navioquatro--;
                    }

                    totalBarcos--;
                    exibirTabuleiro(tabuleiro);

                    break;

            }
        }
    }
    public static void alocarNaviosAutomaticamente(char[][] tabuleiro) {
        Random random = new Random();
        int naviosAlocados = 0;
        int NUM_NAVIOS = 20;

        while (naviosAlocados < NUM_NAVIOS) {
            int linha = random.nextInt(10);
            int coluna = random.nextInt(10);

            if (tabuleiro[linha][coluna] != 'N') {
                tabuleiro[linha][coluna] = 'N';
                naviosAlocados++;
            }
        }
    }
}
